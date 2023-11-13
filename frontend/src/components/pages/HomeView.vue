<template>
  <div class="home">
    <Home />
  </div>
</template>

<script>
// @ is an alias to /src
import Home from '@/components/templates/HomeComponent.vue'


export default {
  name: 'HomeView',
  components: {
    Home
  }
}
</script>
