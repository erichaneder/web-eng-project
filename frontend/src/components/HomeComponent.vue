<template>
  <div class="min-h-screen py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <NormalHeading text="Welcome to Sneaker Shop" />
      <p class="text-gray-600 mb-4">
        Step into the world of style and comfort. Explore our diverse collection of top-tier sneakers perfect for every occasion.
      </p>
    </div>
    
      <!-- Featured Products Section -->
      <div class="mb-10 w-full max-w-6xl flex flex-col items-center">
        <SubHeading text="Featured Products" />
        <FeaturedProducts />
      </div>

      <CustomerTestimonials />
  </div>
</template>

<script>
  import CustomerTestimonials from "@/components/molecules/CustomerTestimonials.vue";
  import FeaturedProducts from "@/components/molecules/FeaturedProducts.vue";
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import SubHeading from "@/components/atoms/SubHeading.vue";

  export default {
    name: "HomeComponent",
    components: {
      CustomerTestimonials,
      FeaturedProducts,
      NormalHeading,
      SubHeading
    }
  };
</script>
  