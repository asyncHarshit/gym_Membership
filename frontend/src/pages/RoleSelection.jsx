import { Activity, ShieldCheck, Stethoscope } from "lucide-react";

const roles = [
  {
    id: "member",
    title: "Member",
    icon: Activity,
    summary: "Buy plans, check in, log workouts, and track progress."
  },
  {
    id: "admin",
    title: "Admin",
    icon: ShieldCheck,
    summary: "Review revenue, manage members, renew plans, and track equipment."
  },
  {
    id: "trainer",
    title: "Trainer",
    icon: Stethoscope,
    summary: "Assign diet plans, create sessions, and review client performance."
  }
];

export default function RoleSelection({ onSelect }) {
  return (
    <main className="role-screen">
      <section className="role-hero">
        <p className="eyebrow">Spring Boot connected frontend</p>
        <h1>gym Tracking</h1>
        <p>Choose a workspace to open the live dashboard experience.</p>
      </section>

      <section className="role-grid">
        {roles.map((role) => {
          const Icon = role.icon;
          return (
            <button className="role-card" key={role.id} onClick={() => onSelect(role.id)}>
              <Icon size={30} />
              <strong>{role.title}</strong>
              <span>{role.summary}</span>
            </button>
          );
        })}
      </section>
    </main>
  );
}
