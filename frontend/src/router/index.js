import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/RegisterView.vue')
  },
  {
    path: '/contact',
    name: 'contact',
    component: () => import(/* webpackChunkName: "contact" */ '../views/ContactView.vue')
  },
  {
    path: '/imprint',
    name: 'imprint',
    component: () => import(/* webpackChunkName: "imprint" */ '../views/ImprintView.vue')
  },
  {
    path: '/help',
    name: 'help',
    component: () => import(/* webpackChunkName: "help" */ '../views/HelpView.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import(/* webpackChunkName: "profile" */ '../views/ProfileView.vue')
  },
  {
    path: '/product/:id',
    name: 'product',
    component: () => import(/* webpackChunkName: "product" */ '../views/ProductDetailView.vue')
  },
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "products" */ '../views/ProductsView.vue')
  },
  {
    path: '/addProduct',
    name: 'addProduct',
    component: () => import(/* webpackChunkName: "addProduct" */ '../views/AddProductView.vue')
  },
  {
    path: '/basket',
    name: 'basket',
    component: () => import(/* webpackChunkName: "basket" */ '../views/BasketView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
