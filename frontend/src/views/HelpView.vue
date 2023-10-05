<template>
    <div class="p-4">
      <h1 class="text-4xl font-bold mb-4">Help & Support</h1>
      <section>
        <h2 class="text-2xl font-semibold mb-2">Order Issues</h2>
        <p>If you have issues with your order, please contact our support team at [support@sneakershop.com](mailto:support@sneakershop.com).</p>
      </section>
      <section class="mt-4">
        <h2 class="text-2xl font-semibold mb-2">Returns & Exchanges</h2>
        <p>Read about our return and exchange policy <router-link to="/return-policy">here</router-link>.</p>
      </section>
      <!-- Add additional sections as needed -->

      <!-- Test Button to trigger Error Modal -->
    <button @click="showErrorModal" class="mt-4 p-2 bg-red-600 text-white rounded">
      Show Error Modal
    </button>

    <!-- Error Modal -->
    <ErrorModal 
      :isVisible="isErrorModalVisible" 
      :errorMessage="'This is a test error message.'"
      @update:isVisible="isErrorModalVisible = $event"
    />

    </div>
  </template>
  
  <script>
  import ErrorModal from '../components/ErrorModal.vue'

  export default {
    name: 'HelpView',
    components: {
        ErrorModal
    },
    data() {
    return {
      isErrorModalVisible: false
    }
    },
    methods: {
        showErrorModal() {
        this.isErrorModalVisible = true;
        }
    }
  }
  </script>
  