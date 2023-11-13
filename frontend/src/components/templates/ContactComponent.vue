<template>
    <div class="min-h-screen flex flex-col items-center p-5">
      <div class="bg-white p-8 rounded-lg shadow-md w-96">
        <NormalHeading text="Contact Us" />
        <form @submit.prevent="submitForm">
          <div class="mb-4">
            <label for="name" class="block text-sm mb-1 text-gray-600">Name:</label>
            <input v-model="name" type="text" id="name" required placeholder="Your name" class="w-full h-10 px-2 border rounded">
          </div>
          <div class="mb-4">
            <label for="email" class="block text-sm mb-1 text-gray-600">Email:</label>
            <input v-model="email" type="email" id="email" required placeholder="Your email" class="w-full h-10 px-2 border rounded">
          </div>
          <div>
            <label for="message" class="block text-sm mb-1 text-gray-600">Message:</label>
            <textarea v-model="message" id="message" required placeholder="Your message" class="w-full h-32 px-2 py-2 border rounded"></textarea>
          </div>
          <CustomButton>Submit</CustomButton>
        </form>
      </div>
    </div>
  </template>
  
<script>
  import CustomButton from '@/components/atoms/Button.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  
  export default {
    data() {
      return {
        name: "",
        email: "",
        message: "",
      };
    },
    components: {
      NormalHeading,
      CustomButton
    },
    methods: {
      submitForm() {
        // Your form submission logic here
        console.log({
          name: this.name,
          email: this.email,
          message: this.message,
        });
      },
    },
  };
</script>
  