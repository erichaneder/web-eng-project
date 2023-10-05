<template>
    <div class="register">
      <Register />
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import Register from '@/components/RegisterComponent.vue'
  
  
  export default {
    name: 'HomeView',
    components: {
      Register
    }
  }
  </script>
  