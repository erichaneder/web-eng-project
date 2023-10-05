<template>
  <div class="home">
    <Login />
  </div>
</template>

<script>
// @ is an alias to /src
import Login from '@/components/LoginComponent.vue'


export default {
  name: 'HomeView',
  components: {
    Login
  }
}
</script>
