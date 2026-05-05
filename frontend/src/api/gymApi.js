import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080",
  headers: {
    "Content-Type": "application/json"
  }
});

export const memberApi = {
  getPlans: () => api.get("/api/member/plans").then((res) => res.data),
  purchasePlan: (planId) =>
    api.post("/api/member/purchase", {
      memberId: 3,
      planId,
      gatewayReference: `pay_frontend_${Date.now()}`
    }).then((res) => res.data),
  checkIn: () =>
    api.post("/api/member/check-in", {
      memberId: 3,
      qrCode: "GYM-ENTRY-2026",
      latitude: 28.6139,
      longitude: 77.2090
    }).then((res) => res.data),
  logWorkout: (payload) =>
    api.post("/api/member/workouts", { memberId: 3, ...payload }).then((res) => res.data),
  getWorkouts: () => api.get("/api/member/3/workouts").then((res) => res.data),
  addMetric: (payload) =>
    api.post("/api/member/metrics", { memberId: 3, ...payload }).then((res) => res.data),
  getProgress: () => api.get("/api/member/3/progress").then((res) => res.data),
  getDietPlans: () => api.get("/api/member/3/diet-plans").then((res) => res.data),
  getSessions: () => api.get("/api/member/sessions").then((res) => res.data),
  bookSession: (sessionId) =>
    api.post(`/api/member/sessions/${sessionId}/book`, { memberId: 3 }).then((res) => res.data)
};

export const adminApi = {
  getDashboard: () => api.get("/api/admin/dashboard").then((res) => res.data),
  getMembers: () => api.get("/api/admin/members").then((res) => res.data),
  renewMember: (payload) => api.post("/api/admin/members/renew", payload).then((res) => res.data),
  getEquipment: () => api.get("/api/admin/equipment").then((res) => res.data)
};

export const trainerApi = {
  assignDietPlan: (payload) =>
    api.post("/api/trainer/diet-plans", { trainerId: 2, ...payload }).then((res) => res.data),
  createSession: (payload) =>
    api.post("/api/trainer/sessions", { trainerId: 2, ...payload }).then((res) => res.data),
  getClientProgress: () => api.get("/api/trainer/clients/3/progress").then((res) => res.data),
  getClientWorkouts: () => api.get("/api/trainer/clients/3/workouts").then((res) => res.data)
};

export function apiErrorMessage(error) {
  return error?.response?.data?.error || error?.message || "Something went wrong";
}
