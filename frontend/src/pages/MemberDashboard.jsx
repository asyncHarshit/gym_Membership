import { useEffect, useMemo, useState } from "react";
import { Activity, CalendarClock, CreditCard, Dumbbell, QrCode, Scale } from "lucide-react";
import { apiErrorMessage, memberApi } from "../api/gymApi";
import DataTable from "../components/DataTable";
import ProgressChart from "../components/ProgressChart";
import StatCard from "../components/StatCard";

const workoutColumns = [
  { key: "workoutDate", label: "Date" },
  { key: "exercise", label: "Exercise" },
  { key: "sets", label: "Sets" },
  { key: "reps", label: "Reps" },
  { key: "weightKg", label: "Weight kg" }
];

export default function MemberDashboard({ notify }) {
  const [plans, setPlans] = useState([]);
  const [workouts, setWorkouts] = useState([]);
  const [progress, setProgress] = useState([]);
  const [dietPlans, setDietPlans] = useState([]);
  const [sessions, setSessions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [workoutForm, setWorkoutForm] = useState({ exercise: "Squat", sets: 4, reps: 8, weightKg: 60 });
  const [metricForm, setMetricForm] = useState({ bodyWeightKg: 76, bodyFatPercent: 22, muscleMassKg: 32 });

  const latestMetric = progress[progress.length - 1];

  const totals = useMemo(() => ({
    workouts: workouts.length,
    sessions: sessions.length,
    weight: latestMetric ? `${latestMetric.bodyWeightKg} kg` : "No data"
  }), [latestMetric, sessions.length, workouts.length]);

  async function load() {
    setLoading(true);
    try {
      const [planData, workoutData, progressData, dietData, sessionData] = await Promise.all([
        memberApi.getPlans(),
        memberApi.getWorkouts(),
        memberApi.getProgress(),
        memberApi.getDietPlans(),
        memberApi.getSessions()
      ]);
      setPlans(planData);
      setWorkouts(workoutData);
      setProgress(progressData);
      setDietPlans(dietData);
      setSessions(sessionData);
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
  }, []);

  async function runAction(action, message) {
    try {
      await action();
      notify(message, "success");
      await load();
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    }
  }

  return (
    <div className="dashboard">
      <header className="page-header">
        <div>
          <p className="eyebrow">Member ID 3</p>
          <h1>Member dashboard</h1>
        </div>
        <button className="primary-action" onClick={() => runAction(memberApi.checkIn, "QR check-in successful")}>
          <QrCode size={18} />
          QR Check-in
        </button>
      </header>

      <section className="stat-grid">
        <StatCard title="Workout logs" value={totals.workouts} detail="Tracked sessions" icon={Activity} />
        <StatCard title="Latest weight" value={totals.weight} detail="From body metrics" icon={Scale} />
        <StatCard title="Open sessions" value={totals.sessions} detail="Ready to book" icon={CalendarClock} />
      </section>

      <section className="section-grid two">
        <article className="panel" id="plans">
          <div className="panel-heading">
            <h2>Membership plans</h2>
            <CreditCard size={20} />
          </div>
          <div className="plan-grid">
            {plans.map((plan) => (
              <div className="plan-card" key={plan.id}>
                <strong>{plan.name}</strong>
                <span>{plan.durationDays} days</span>
                <p>₹{plan.price}</p>
                <button onClick={() => runAction(() => memberApi.purchasePlan(plan.id), "Membership purchased")}>
                  Buy Plan
                </button>
              </div>
            ))}
          </div>
        </article>

        <article className="panel" id="workouts">
          <div className="panel-heading">
            <h2>Log workout</h2>
            <Dumbbell size={20} />
          </div>
          <form className="form-grid" onSubmit={(event) => {
            event.preventDefault();
            runAction(() => memberApi.logWorkout({
              ...workoutForm,
              sets: Number(workoutForm.sets),
              reps: Number(workoutForm.reps),
              weightKg: Number(workoutForm.weightKg)
            }), "Workout logged");
          }}>
            <label>
              Exercise
              <input value={workoutForm.exercise} onChange={(event) => setWorkoutForm({ ...workoutForm, exercise: event.target.value })} />
            </label>
            <label>
              Sets
              <input type="number" min="1" value={workoutForm.sets} onChange={(event) => setWorkoutForm({ ...workoutForm, sets: event.target.value })} />
            </label>
            <label>
              Reps
              <input type="number" min="1" value={workoutForm.reps} onChange={(event) => setWorkoutForm({ ...workoutForm, reps: event.target.value })} />
            </label>
            <label>
              Weight kg
              <input type="number" min="1" value={workoutForm.weightKg} onChange={(event) => setWorkoutForm({ ...workoutForm, weightKg: event.target.value })} />
            </label>
            <button className="primary-action" type="submit">Save workout</button>
          </form>
        </article>
      </section>

      <section className="panel" id="progress">
        <div className="panel-heading">
          <h2>Progress analytics</h2>
          <Scale size={20} />
        </div>
        <form className="metric-row" onSubmit={(event) => {
          event.preventDefault();
          runAction(() => memberApi.addMetric({
            bodyWeightKg: Number(metricForm.bodyWeightKg),
            bodyFatPercent: Number(metricForm.bodyFatPercent),
            muscleMassKg: Number(metricForm.muscleMassKg)
          }), "Metric added");
        }}>
          <input type="number" step="0.1" value={metricForm.bodyWeightKg} onChange={(event) => setMetricForm({ ...metricForm, bodyWeightKg: event.target.value })} aria-label="Body weight" />
          <input type="number" step="0.1" value={metricForm.bodyFatPercent} onChange={(event) => setMetricForm({ ...metricForm, bodyFatPercent: event.target.value })} aria-label="Body fat" />
          <input type="number" step="0.1" value={metricForm.muscleMassKg} onChange={(event) => setMetricForm({ ...metricForm, muscleMassKg: event.target.value })} aria-label="Muscle mass" />
          <button type="submit">Add metric</button>
        </form>
        <ProgressChart data={progress} />
      </section>

      <section className="section-grid two">
        <article className="panel">
          <div className="panel-heading">
            <h2>Workout history</h2>
          </div>
          <DataTable columns={workoutColumns} rows={workouts} empty={loading ? "Loading workouts..." : "No workouts logged yet"} />
        </article>

        <article className="panel" id="sessions">
          <div className="panel-heading">
            <h2>Available sessions</h2>
          </div>
          <div className="stack-list">
            {sessions.length ? sessions.map((session) => (
              <div className="list-item" key={session.id}>
                <div>
                  <strong>{session.sessionType}</strong>
                  <span>{new Date(session.startsAt).toLocaleString()} · capacity {session.capacity}</span>
                </div>
                <button onClick={() => runAction(() => memberApi.bookSession(session.id), "Session booked")}>Book</button>
              </div>
            )) : <div className="empty-state">No open sessions right now</div>}
          </div>
        </article>
      </section>

      <section className="panel">
        <div className="panel-heading">
          <h2>Diet plans</h2>
        </div>
        <div className="stack-list">
          {dietPlans.length ? dietPlans.map((plan) => (
            <div className="list-item diet" key={plan.id}>
              <div>
                <strong>{plan.title}</strong>
                <span>{plan.assignedDate}</span>
              </div>
              <p>{plan.meals}</p>
            </div>
          )) : <div className="empty-state">No diet plans assigned yet</div>}
        </div>
      </section>
    </div>
  );
}
