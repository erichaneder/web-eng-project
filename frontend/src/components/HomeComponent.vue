<template>
  <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <h1 class="text-2xl font-bold mb-5 text-pink-800">Welcome to Sneaker Shop</h1>
      <p class="text-gray-600 mb-4">
        Step into the world of style and comfort. Explore our diverse collection of top-tier sneakers perfect for every occasion.
      </p>
    </div>
    
      <!-- Featured Products Section -->
      <div class="mb-10 w-full max-w-6xl flex flex-col items-center">
        <h2 class="text-xl font-bold mb-4 text-pink-800">Featured Products</h2>
        <FeaturedProducts />
      </div>

      <CustomerTestimonials />
  </div>
</template>

<script>
  import CustomerTestimonials from "@/components/molecules/CustomerTestimonials.vue";
  import FeaturedProducts from "@/components/molecules/FeaturedProducts.vue";

  export default {
    name: "HomeComponent",
    components: {
      CustomerTestimonials,
      FeaturedProducts
    }
  };
</script>
  