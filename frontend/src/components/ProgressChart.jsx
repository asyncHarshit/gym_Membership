import {
  CartesianGrid,
  Legend,
  Line,
  LineChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis
} from "recharts";

export default function ProgressChart({ data }) {
  if (!data?.length) {
    return <div className="empty-state">Add body metrics to see progress charts.</div>;
  }

  return (
    <div className="chart-shell">
      <ResponsiveContainer width="100%" height={320}>
        <LineChart data={data} margin={{ top: 10, right: 20, bottom: 0, left: -12 }}>
          <CartesianGrid strokeDasharray="3 3" stroke="#e2e8f0" />
          <XAxis dataKey="metricDate" tick={{ fill: "#64748b", fontSize: 12 }} />
          <YAxis tick={{ fill: "#64748b", fontSize: 12 }} />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="bodyWeightKg" name="Weight kg" stroke="#0f766e" strokeWidth={3} />
          <Line type="monotone" dataKey="bodyFatPercent" name="Body fat %" stroke="#f97316" strokeWidth={3} />
          <Line type="monotone" dataKey="muscleMassKg" name="Muscle kg" stroke="#2563eb" strokeWidth={3} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}
