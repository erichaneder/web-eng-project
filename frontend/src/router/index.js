import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '@/components/pages/HomeView.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/components/pages/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '@/components/pages/LoginView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '@/components/pages/RegisterView.vue')
  },
  {
    path: '/contact',
    name: 'contact',
    component: () => import(/* webpackChunkName: "contact" */ '@/components/pages/ContactView.vue')
  },
  {
    path: '/imprint',
    name: 'imprint',
    component: () => import(/* webpackChunkName: "imprint" */ '@/components/pages/ImprintView.vue')
  },
  {
    path: '/help',
    name: 'help',
    component: () => import(/* webpackChunkName: "help" */ '@/components/pages/HelpView.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import(/* webpackChunkName: "profile" */ '@/components/pages/ProfileView.vue')
  },
  {
    path: '/product/:id',
    name: 'product',
    component: () => import(/* webpackChunkName: "product" */ '@/components/pages/ProductDetailView.vue')
  },
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "products" */ '@/components/pages/ProductsView.vue')
  },
  {
    path: '/addProduct',
    name: 'addProduct',
    component: () => import(/* webpackChunkName: "addProduct" */ '@/components/pages/AddProductView.vue'),
    meta: { requiresAdmin: true } // This route requires admin access
  },
  {
    path: '/basket',
    name: 'basket',
    component: () => import(/* webpackChunkName: "basket" */ '@/components/pages/BasketView.vue')
  },
  {
    path: '/unauthorized',
    name: 'unauthorized',
    component: () => import(/* webpackChunkName: "unauthorized" */ '@/components/pages/UnauthorizedView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const userRole = sessionStorage.getItem('userRole');
  const isAdmin = userRole === 'ROLE_ADMIN'; // Assuming 'ROLE_ADMIN' is your admin role identifier

  if (to.matched.some(record => record.meta.requiresAdmin) && !isAdmin) {
    // If the route requires admin access and the user is not an admin, redirect them
    next({ path: '/unauthorized' }); // Redirect to an 'unauthorized' route or show a message
  } else {
    // Otherwise, proceed as normal
    next();
  }
});

export default router
