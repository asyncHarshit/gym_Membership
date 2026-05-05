import {
  Activity,
  BarChart3,
  CalendarClock,
  Dumbbell,
  Home,
  ShieldCheck,
  Stethoscope,
  Users,
  Wrench
} from "lucide-react";

const roleMeta = {
  member: {
    label: "Member",
    icon: Dumbbell,
    items: [
      ["Plans", Home],
      ["Check-in", ShieldCheck],
      ["Workouts", Activity],
      ["Progress", BarChart3],
      ["Sessions", CalendarClock]
    ]
  },
  admin: {
    label: "Admin",
    icon: Users,
    items: [
      ["Dashboard", Home],
      ["Members", Users],
      ["Renewals", ShieldCheck],
      ["Equipment", Wrench]
    ]
  },
  trainer: {
    label: "Trainer",
    icon: Stethoscope,
    items: [
      ["Clients", Users],
      ["Diet Plans", Activity],
      ["Sessions", CalendarClock],
      ["Progress", BarChart3]
    ]
  }
};

export default function Sidebar({ role, onRoleChange }) {
  const meta = roleMeta[role];
  const RoleIcon = meta.icon;

  return (
    <aside className="sidebar">
      <button className="brand" onClick={() => onRoleChange(null)}>
        <span className="brand-mark">
          <Dumbbell size={22} />
        </span>
        <span>
          <strong>gym Tracking</strong>
          <small>{meta.label} workspace</small>
        </span>
      </button>

      <div className="role-pill">
        <RoleIcon size={18} />
        <span>Demo {meta.label}</span>
      </div>

      <nav className="nav-list">
        {meta.items.map(([label, Icon]) => (
          <a href={`#${label.toLowerCase().replaceAll(" ", "-")}`} key={label}>
            <Icon size={18} />
            <span>{label}</span>
          </a>
        ))}
      </nav>

      <button className="switch-role" onClick={() => onRoleChange(null)}>
        Switch role
      </button>
    </aside>
  );
}
