import { logPlugin } from '@babel/preset-env/lib/debug'
import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Pocetna from '../views/Pocetna.vue'
import Login from '../views/Login.vue'
import Registracija from '../views/Registracija.vue'
import Kupac from '../views/Kupac.vue'
import Menadzer from '../views/Menadzer.vue'
import PrikazSvogNaloga from '../views/PrikazSvogNaloga.vue'
import IzmenaSvogNaloga from '../views/IzmenaSvogNaloga.vue'
import Dostavljac from '../views/Dostavljac.vue'
import Admin from '../views/Admin.vue'
import Restoran from '../views/Restoran.vue'
import PregledSvhKorisnika from '../views/PregledSvihKorisnika.vue'



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
  },
  {
    path: '/kupac',
    name: 'Kupac',
    component: Kupac
  },
  {
    path: '/menadzer',
    name: 'Menadzer',
    component: Menadzer
  },
  {
    path: '/moj_nalog',
    name: 'PrikazSvogNaloga',
    component: PrikazSvogNaloga

  },
  {
    path: '/izmeni_nalog',
    name: 'IzmenaSvogNaloga',
    component: IzmenaSvogNaloga

  },
  {
    path: '/dostavljac',
    name: 'Dostavljac',
    component: Dostavljac

  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin

  },
  {
    path: '/restoran/:id',
    name: 'Restoran',
    component: () => import(/* webpackChunkName: "artikliRestorana" */ '../views/Restoran.vue')

  },
  {
    path: '/svi_korisnici',
    name: 'PregledSvihKorisnika',
    component: PregledSvhKorisnika

  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
