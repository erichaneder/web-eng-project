<template>
  <div class="about">
    <About />
  </div>
</template>

<script>
// @ is an alias to /src
import About from '@/components/templates/AboutComponent.vue'


export default {
  name: 'AboutView',
  components: {
    About
  }
}
</script>
