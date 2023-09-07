import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Index from '../views/Index.vue'
import Appointment from '../views/Appointment.vue'
import ReportList from '../views/ReportList.vue'
import Personal from '../views/Personal.vue'
import Hospital from '../views/Hospital.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/index',
    name:'Index',
    component:Index
  },
  {
    path: '/appointment',
    name:'Appointment',
    component:Appointment
  },
  {
    path: '/reportList',
    name:'ReportList',
    component:ReportList
  },
  {
    path: '/personal',
    name:'Personal',
    component:Personal
  },
  {
    path: '/hospital',
    name:'Hospital',
    component:Hospital
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router