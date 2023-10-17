<template>
  <div class="min-h-screen flex flex-col items-center bg-gray-100 p-5">
    <div class="bg-white p-8 rounded-lg shadow-md w-96">
      <NormalHeading :text="product.name" />

      <img :src="product.image" :alt="product.name" class="w-full h-52 object-cover rounded mb-4"/>
      <p class="text-gray-600 mb-4">{{ product.description }}</p>
      <p class="text-pink-800 font-bold mb-4">${{ product.price }}</p>

      <!-- Add to Cart button -->
      <button @click="addToCart" class="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Add to Cart</button>
    </div>
  </div>
</template>

<script>
import NormalHeading from "@/components/atoms/NormalHeading.vue";

export default {
  props: ['id'],
  data() {
    return {
      product: {}
    };
  },
  components: {
    NormalHeading
  },
  mounted() {
    // Fetch product data using the product ID
    //const productId = this.id;
    //this.product = this.$store.getters.getProductById(productId); // Assuming you have a Vuex store setup. If not, fetch it from your data source accordingly.
  },
  methods: {
    addToCart() {
      // Logic for adding the product to the cart
    }
  }
}
</script>