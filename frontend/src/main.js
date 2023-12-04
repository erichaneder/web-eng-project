import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import './index.css'
import router from './router'

const piniaStore = createPinia();

const app = createApp(App);
app.use(piniaStore);
app.use(router);
app.mount('#app');