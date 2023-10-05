<template>
    <div v-if="isVisible" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-4 rounded shadow-md w-96">
        <p class="text-red-600 font-bold">{{ errorMessage }}</p>
        <button @click="closeModal" class="mt-4 p-2 bg-red-600 text-white rounded">Close</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      isVisible: Boolean,
      errorMessage: String
    },
    methods: {
      closeModal() {
        this.$emit('update:isVisible', false);
      }
    }
  }
  </script>
  