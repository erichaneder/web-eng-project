import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import './index.css'
import router from './router'
import lazyload from 'vue-lazyload';

const piniaStore = createPinia();

const app = createApp(App);
app.use(piniaStore);
app.use(router);
app.use(lazyload);
app.mount('#app');