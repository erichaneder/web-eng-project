<template>
  <div class="p-4 min-h-screen bg-gray-100 flex flex-col items-center mt-12">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-3xl">
      <NormalHeading text="Help & Support" />
      <div class="h-0.5 bg-gray-200 mx-auto mt-2.5 mb-4"></div>
      <SubHeading text="Order Issues" />
      <p>If you have issues with your order, please contact our support team at <a href="mailto:support@sneakershop.com">support@sneakershop.com</a>.</p>
    
      <SubHeading text="Returns & Exchanges" />
      <p>Read about our return and exchange policy here.</p>

      <SubHeading text="Shipping Information" />
      <p>For details on shipping methods, timelines, and prices, visit our Shipping Information page.</p>

      <SubHeading text="Tracking Your Order" />
      <p>Once your order is shipped, you'll receive a tracking number via email. Use this number on our Order Tracking page to check the status of your shipment.</p>

      <SubHeading text="Payment Methods" />
      <p>We accept various payment methods including Visa, MasterCard, PayPal, and more. Check out our Payment Options page for a full list.</p>

      <SubHeading text="Account & Settings" />
      <p>Need help managing your account? Learn about updating your settings, changing your password, and more in our Account Help section.</p>

      <SubHeading text="FAQs" />
      <p>For commonly asked questions, visit our Frequently Asked Questions (FAQ) page.</p>
    
      <!-- Test Button to trigger Error Modal -->
      <CustomButton @click="showErrorModal" customButtonStyle="mt-4 p-2 bg-red-600 text-white rounded">Contact Support</CustomButton>
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
import CustomButton from '@/components/atoms/Button.vue';
import ErrorModal from '@/components/atoms/ErrorModal.vue'
import NormalHeading from "@/components/atoms/NormalHeading.vue";
import SubHeading from "@/components/atoms/SubHeading.vue";

export default {
  name: 'HelpView',
  components: {
      ErrorModal,
      NormalHeading,
      SubHeading,
      CustomButton
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
