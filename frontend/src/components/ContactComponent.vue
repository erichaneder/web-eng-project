<template>
    <div class="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-5">
      <div class="bg-white p-8 rounded-lg shadow-md w-96">
        <h1 class="text-2xl font-bold mb-5 text-pink-800">Contact Us</h1>
        <form @submit.prevent="submitForm">
          <div class="mb-4">
            <label for="name" class="block text-sm mb-1 text-gray-600">Name:</label>
            <input v-model="name" type="text" id="name" required placeholder="Your name" class="w-full h-10 px-2 border rounded">
          </div>
          <div class="mb-4">
            <label for="email" class="block text-sm mb-1 text-gray-600">Email:</label>
            <input v-model="email" type="email" id="email" required placeholder="Your email" class="w-full h-10 px-2 border rounded">
          </div>
          <div class="mb-4">
            <label for="message" class="block text-sm mb-1 text-gray-600">Message:</label>
            <textarea v-model="message" id="message" required placeholder="Your message" class="w-full h-32 px-2 py-2 border rounded"></textarea>
          </div>
          <button type="submit" class="w-full bg-pink-800 text-white p-2 rounded hover:bg-pink-700">Send Message</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        name: "",
        email: "",
        message: "",
      };
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
  