import { createRouter, createWebHistory } from 'vue-router'
import { useCompleteStore } from '@/store/store';

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "home" */ '@/components/pages/HomeView.vue')
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
    path: '/order/:id',
    name: 'order',
    component: () => import(/* webpackChunkName: "product" */ '@/components/pages/OrderDetailView.vue')
  },
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "products" */ '@/components/pages/ProductsView.vue')
  },
  {
    path: '/adminProducts',
    name: 'adminProducts',
    component: () => import(/* webpackChunkName: "adminProducts" */ '@/components/pages/AdminProductsView.vue'),
    meta: { requiresAdmin: true } // This route requires admin access
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
    path: '/users',
    name: 'users',
    component: () => import(/* webpackChunkName: "users" */ '@/components/pages/UsersView.vue'),
    meta: { requiresAdmin: true } // This route requires admin access
  },
  {
    path: '/orders',
    name: 'orders',
    component: () => import(/* webpackChunkName: "orders" */ '@/components/pages/OrdersView.vue'),
    meta: { requiresAdmin: true } // This route requires admin access
  },
  {
    path: '/user/:userId',
    name: 'user',
    component: () => import(/* webpackChunkName: "user" */ '@/components/pages/EditUserView.vue'),
    meta: { requiresAdmin: true } // This route requires admin access
  },
  {
    path: '/product/edit/:productId',
    name: 'editProduct',
    component: () => import(/* webpackChunkName: "editProduct" */ '@/components/pages/EditProductView.vue'),
    meta: { requiresAdmin: true } // This route requires admin access
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
  const store = useCompleteStore();
  const isAdmin = store.isLoggedIn;

  if (to.matched.some(record => record.meta.requiresAdmin) && !isAdmin) {
    // If the route requires admin access and the user is not an admin, redirect them
    next({ path: '/unauthorized' });
  } else {
    // Otherwise, proceed as normal
    next();
  }
});

export default router
