<template>
    <button :class="['custom-btn',customButtonStyle]" @click="onClick">
        <!-- Used to set custom text or symbols in the button -->
        <slot/>
    </button>
  </template>
  
  <script>
  export default {
    name: 'CustomButton',
    props: {
     
      customButtonStyle: {
        type: String,
        default: 'w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mt-3' 
      }
    },
    methods: {
      onClick() {
        this.$emit('clicked');
      },
    },
  };
  </script>