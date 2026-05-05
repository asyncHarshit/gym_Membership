import { useEffect, useState } from "react";
import { CalendarClock, ClipboardList, Stethoscope } from "lucide-react";
import { apiErrorMessage, trainerApi } from "../api/gymApi";
import DataTable from "../components/DataTable";
import ProgressChart from "../components/ProgressChart";

const workoutColumns = [
  { key: "workoutDate", label: "Date" },
  { key: "exercise", label: "Exercise" },
  { key: "sets", label: "Sets" },
  { key: "reps", label: "Reps" },
  { key: "weightKg", label: "Weight kg" }
];

export default function TrainerDashboard({ notify }) {
  const [progress, setProgress] = useState([]);
  const [workouts, setWorkouts] = useState([]);
  const [diet, setDiet] = useState({
    memberId: 3,
    title: "Lean strength plan",
    meals: "Breakfast: oats and eggs; Lunch: dal, rice, salad; Dinner: paneer and vegetables"
  });
  const [session, setSession] = useState({
    sessionType: "Personal Training",
    startsAt: new Date(Date.now() + 86400000).toISOString().slice(0, 16),
    capacity: 1
  });

  async function load() {
    try {
      const [progressData, workoutData] = await Promise.all([
        trainerApi.getClientProgress(),
        trainerApi.getClientWorkouts()
      ]);
      setProgress(progressData);
      setWorkouts(workoutData);
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    }
  }

  useEffect(() => {
    load();
  }, []);

  async function submitDiet(event) {
    event.preventDefault();
    try {
      await trainerApi.assignDietPlan({ ...diet, memberId: Number(diet.memberId) });
      notify("Diet plan assigned", "success");
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    }
  }

  async function submitSession(event) {
    event.preventDefault();
    try {
      await trainerApi.createSession({ ...session, capacity: Number(session.capacity) });
      notify("Training session created", "success");
    } catch (error) {
      notify(apiErrorMessage(error), "error");
    }
  }

  return (
    <div className="dashboard">
      <header className="page-header">
        <div>
          <p className="eyebrow">Trainer ID 2 · Client ID 3</p>
          <h1>Trainer dashboard</h1>
        </div>
      </header>

      <section className="section-grid two">
        <article className="panel" id="diet-plans">
          <div className="panel-heading">
            <h2>Assign diet plan</h2>
            <ClipboardList size={20} />
          </div>
          <form className="form-grid" onSubmit={submitDiet}>
            <label>
              Member ID
              <input type="number" value={diet.memberId} onChange={(event) => setDiet({ ...diet, memberId: event.target.value })} />
            </label>
            <label>
              Title
              <input value={diet.title} onChange={(event) => setDiet({ ...diet, title: event.target.value })} />
            </label>
            <label className="span-2">
              Meals
              <textarea value={diet.meals} onChange={(event) => setDiet({ ...diet, meals: event.target.value })} />
            </label>
            <button className="primary-action" type="submit">Assign plan</button>
          </form>
        </article>

        <article className="panel" id="sessions">
          <div className="panel-heading">
            <h2>Create session</h2>
            <CalendarClock size={20} />
          </div>
          <form className="form-grid" onSubmit={submitSession}>
            <label>
              Session type
              <input value={session.sessionType} onChange={(event) => setSession({ ...session, sessionType: event.target.value })} />
            </label>
            <label>
              Starts at
              <input type="datetime-local" value={session.startsAt} onChange={(event) => setSession({ ...session, startsAt: event.target.value })} />
            </label>
            <label>
              Capacity
              <input type="number" min="1" value={session.capacity} onChange={(event) => setSession({ ...session, capacity: event.target.value })} />
            </label>
            <button className="primary-action" type="submit">Create slot</button>
          </form>
        </article>
      </section>

      <section className="panel" id="progress">
        <div className="panel-heading">
          <h2>Client progress</h2>
          <Stethoscope size={20} />
        </div>
        <ProgressChart data={progress} />
      </section>

      <section className="panel" id="clients">
        <div className="panel-heading">
          <h2>Client workout logs</h2>
        </div>
        <DataTable columns={workoutColumns} rows={workouts} />
      </section>
    </div>
  );
}
