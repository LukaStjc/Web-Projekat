import { logPlugin } from '@babel/preset-env/lib/debug'
import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Pocetna from '../views/Pocetna.vue'
import Login from '../views/Login.vue'
import Registracija from '../views/Registracija.vue'

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/',
    name: 'Pocetna',
    component: Pocetna
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/registracija',
    name: 'Registracija',
    component: Registracija
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
