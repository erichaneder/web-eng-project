<template>
  <div v-if="isVisible" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
    <div class="bg-white p-4 rounded shadow-md w-96">
      <SubHeading text="Change Password" />
      <input type="password" id="newPassword" placeholder="New Password" v-model="newPassword" class="w-full h-10 px-2 border rounded" required>
      <input type="password" id="confirmPassword" placeholder="Confirm New Password" v-model="confirmPassword" class="w-full h-10 px-2 mt-4 border rounded" required>
      <div class="text-red-500" v-if="error">{{ error }}</div>
      <CustomButton @clicked="changePassword" customButtonStyle="mt-4 mr-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Submit</CustomButton>
      <CustomButton @clicked="closeModal" customButtonStyle="mt-4 bg-gray-300 text-black p-2 rounded hover:bg-gray-400">Cancel</CustomButton>
    </div>
  </div>
</template>
  
<script>
import CustomButton from '@/components/atoms/Button.vue';
import SubHeading from "@/components/atoms/SubHeading.vue";

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  components: {
    CustomButton,
    SubHeading
  },
  data() {
    return {
      newPassword: '',
      confirmPassword: '',
      error: ''
    };
  },
  methods: {
    changePassword() {
      const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;
      if (!passwordPattern.test(this.newPassword) || this.newPassword.length < 12) {
        this.error = 'Password must be 12 characters long and must include uppercase, lowercase, numbers, and symbols';
      } else if (this.newPassword !== this.confirmPassword) {
        this.error = "Passwords do not match!";
        console.log("PW1: " + this.newPassword + ", PW2: " + this.confirmPassword);
      } else {
        this.$emit('changePassword', this.newPassword);
        this.closeModal();
      }
    },
    closeModal() {
      this.newPassword = '',
      this.confirmPassword = ''
      this.error = ''
      this.$emit('update:isVisible', false);
    }
  }
};
</script>
  