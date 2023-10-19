<template>
  <div class="p-4 min-h-screen bg-gray-100 flex flex-col items-center mt-12">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-3xl">
      <NormalHeading text="Help & Support" />
      <div class="h-0.5 bg-gray-200 mx-auto mt-2.5 mb-4"></div>
      <SubHeading text="Order Issues" />
      <p>If you have issues with your order, please contact our support team at <a href="mailto:support@sneakershop.com">support@sneakershop.com</a>.</p>
    
      <SubHeading text="Returns & Exchanges" />
      <p>Read about our return and exchange policy <router-link to="/return-policy">here</router-link>.</p>

      <SubHeading text="Shipping Information" />
      <p>For details on shipping methods, timelines, and prices, visit our <router-link to="/shipping-info">Shipping Information</router-link> page.</p>

      <SubHeading text="Tracking Your Order" />
      <p>Once your order is shipped, you'll receive a tracking number via email. Use this number on our <router-link to="/order-tracking">Order Tracking</router-link> page to check the status of your shipment.</p>

      <SubHeading text="Payment Methods" />
      <p>We accept various payment methods including Visa, MasterCard, PayPal, and more. Check out our <router-link to="/payment-options">Payment Options</router-link> page for a full list.</p>

      <SubHeading text="Account & Settings" />
      <p>Need help managing your account? Learn about updating your settings, changing your password, and more in our <router-link to="/account-help">Account Help</router-link> section.</p>

      <SubHeading text="FAQs" />
      <p>For commonly asked questions, visit our <router-link to="/faqs">Frequently Asked Questions (FAQ)</router-link> page.</p>
    
      <!-- Test Button to trigger Error Modal -->
      <button @click="showErrorModal" class="mt-4 p-2 bg-red-600 text-white rounded">
        Contact Support
      </button>
    </div>
      

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
