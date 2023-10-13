<template>
    <div class="p-4 min-h-screen bg-gray-100">
      <NormalHeading text="Imprint" />
      <p>Company Name: SneakerShop Inc.</p>
      <p>Address: 123 Shoe Street, Sneaker City, SC 12345</p>
      <!-- Add other legal information and details here -->
    </div>
  </template>
  
  <script>
  import NormalHeading from "@/components/atoms/NormalHeading.vue";

  export default {
    name: 'ImprintView',
    components: {
      NormalHeading
    }
  }
  </script>