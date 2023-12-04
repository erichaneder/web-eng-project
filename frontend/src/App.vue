<template>
  <div class="bg-gray-100">
    <Navbar />
    <router-view />
    <Footer />
  </div>
</template>

<script>
  import Navbar from './components/templates/NavbarComponent.vue'
  import Footer from './components/templates/FooterComponent.vue'
  import { useCompleteStore } from '@/store/store';

  export default {
    name: 'App',
    components: {
      Navbar,
      Footer
    },
    data() {
      return {
        store: useCompleteStore()
      }  
    },
    mounted() {
    this.store.fetchProducts();
  }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
