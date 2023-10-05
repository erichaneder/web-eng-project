<template>
  <div class="about">
    <About />
  </div>
</template>

<script>
// @ is an alias to /src
import About from '@/components/AboutComponent.vue'


export default {
  name: 'AboutView',
  components: {
    About
  }
}
</script>
