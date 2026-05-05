import { useState } from "react";
import Sidebar from "./components/Sidebar";
import Toast from "./components/Toast";
import AdminDashboard from "./pages/AdminDashboard";
import MemberDashboard from "./pages/MemberDashboard";
import RoleSelection from "./pages/RoleSelection";
import TrainerDashboard from "./pages/TrainerDashboard";

export default function App() {
  const [role, setRole] = useState(null);
  const [toast, setToast] = useState(null);

  function notify(message, type = "success") {
    setToast({ message, type });
    window.clearTimeout(window.__gymToastTimer);
    window.__gymToastTimer = window.setTimeout(() => setToast(null), 3200);
  }

  if (!role) {
    return (
      <>
        <RoleSelection onSelect={setRole} />
        <Toast toast={toast} onClose={() => setToast(null)} />
      </>
    );
  }

  return (
    <div className="app-shell">
      <Sidebar role={role} onRoleChange={setRole} />
      <main className="workspace">
        {role === "member" && <MemberDashboard notify={notify} />}
        {role === "admin" && <AdminDashboard notify={notify} />}
        {role === "trainer" && <TrainerDashboard notify={notify} />}
      </main>
      <Toast toast={toast} onClose={() => setToast(null)} />
    </div>
  );
}
