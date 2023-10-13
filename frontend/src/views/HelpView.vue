<template>
    <div class="p-4 min-h-screen bg-gray-100">
      <NormalHeading text="Help & Support" />
      <section>
        <SubHeading text="Order Issues" />
        <p>If you have issues with your order, please contact our support team at [support@sneakershop.com](mailto:support@sneakershop.com).</p>
      </section>
      <section class="mt-4">
        <SubHeading text="Returns & Exchanges" />
        <p>Read about our return and exchange policy <router-link to="/return-policy">here</router-link>.</p>
      </section>
      <!-- Add additional sections as needed -->

      <!-- Test Button to trigger Error Modal -->
    <button @click="showErrorModal" class="mt-4 p-2 bg-red-600 text-white rounded">
      Contact Support
    </button>

    <!-- Error Modal -->
    <ErrorModal 
      :isVisible="isErrorModalVisible" 
      :errorMessage="'Error: 404 Support not found.'"
      @update:isVisible="isErrorModalVisible = $event"
    />

    </div>
  </template>
  
  <script>
  import ErrorModal from '../components/ErrorModal.vue'
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import SubHeading from "@/components/atoms/SubHeading.vue";

  export default {
    name: 'HelpView',
    components: {
        ErrorModal,
        NormalHeading,
        SubHeading
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
  