import { useEffect, useState } from "react";
import { Banknote, CalendarClock, ShieldCheck, Users, Wrench } from "lucide-react";
import { adminApi, apiErrorMessage, memberApi } from "../api/gymApi";
import DataTable from "../components/DataTable";
import StatCard from "../components/StatCard";

const memberColumns = [
  { key: "id", label: "ID" },
  { key: "name", label: "Name" },
  { key: "email", label: "Email" },
  { key: "phone", label: "Phone" },
  { key: "role", label: "Role" }
];

const equipmentColumns = [
  { key: "id", label: "ID" },
  { key: "name", label: "Equipment" },
  { key: "status", label: "Status", render: (row) => <span className={`status ${row.status.toLowerCase()}`}>{row.status}</span> },
  { key: "nextMaintenanceDate", label: "Next maintenance" }
];

export default function AdminDashboard({ notify }) {
  const [stats, setStats] = useState(null);
  const [members, setMembers] = useState([]);
  const [equipment, setEquipment] = useState([]);
  const [plans, setPlans] = useState([]);
  const [renewal, setRenewal] = useState({ memberId: 3, planId: 1, gatewayReference: "renew_frontend" });

  async function load() {
    try {
      const [dashboardData, memberData, equipmentData, planData] = await Promise.all([
        adminApi.getDashboard(),
        adminApi.getMembers(),
        adminApi.getEquipment(),
        memberApi.getPlans()
      ]);
      setStats(dashboardData);
      setMembers(memberData);
      setEquipment(equipmentData);
      setPlans(planData);
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    }
  }

  useEffect(() => {
    load();
  }, []);

  async function renew(event) {
    event.preventDefault();
    try {
      await adminApi.renewMember({
        memberId: Number(renewal.memberId),
        planId: Number(renewal.planId),
        gatewayReference: renewal.gatewayReference
      });
      notify("Subscription renewed", "success");
      await load();
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    }
  }

  return (
    <div className="dashboard">
      <header className="page-header">
        <div>
          <p className="eyebrow">Admin ID 1</p>
          <h1>Admin dashboard</h1>
        </div>
      </header>

      <section className="stat-grid" id="dashboard">
        <StatCard title="Active members" value={stats?.activeMembers ?? "-"} detail="Memberships active" icon={Users} />
        <StatCard title="Today check-ins" value={stats?.todayCheckIns ?? "-"} detail="Entrance scans" icon={ShieldCheck} />
        <StatCard title="Expiring soon" value={stats?.expiringThisWeek ?? "-"} detail="Next 7 days" icon={CalendarClock} />
        <StatCard title="Today revenue" value={`₹${stats?.todayRevenue ?? 0}`} detail="Payments captured" icon={Banknote} />
      </section>

      <section className="section-grid two">
        <article className="panel" id="members">
          <div className="panel-heading">
            <h2>Members</h2>
            <Users size={20} />
          </div>
          <DataTable columns={memberColumns} rows={members} />
        </article>

        <article className="panel" id="renewals">
          <div className="panel-heading">
            <h2>Renew subscription</h2>
            <ShieldCheck size={20} />
          </div>
          <form className="form-grid" onSubmit={renew}>
            <label>
              Member ID
              <input type="number" value={renewal.memberId} onChange={(event) => setRenewal({ ...renewal, memberId: event.target.value })} />
            </label>
            <label>
              Plan
              <select value={renewal.planId} onChange={(event) => setRenewal({ ...renewal, planId: event.target.value })}>
                {plans.map((plan) => (
                  <option key={plan.id} value={plan.id}>{plan.name}</option>
                ))}
              </select>
            </label>
            <label>
              Payment reference
              <input value={renewal.gatewayReference} onChange={(event) => setRenewal({ ...renewal, gatewayReference: event.target.value })} />
            </label>
            <button className="primary-action" type="submit">Renew member</button>
          </form>
        </article>
      </section>

      <section className="panel" id="equipment">
        <div className="panel-heading">
          <h2>Equipment maintenance</h2>
          <Wrench size={20} />
        </div>
        <DataTable columns={equipmentColumns} rows={equipment} />
      </section>
    </div>
  );
}
