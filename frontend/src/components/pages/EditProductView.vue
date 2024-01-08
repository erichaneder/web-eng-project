<template>
    <div class="container mx-auto my-8 px-4 mb-40 flex flex-col items-center">
      <NormalHeading text="Edit Product"/>
  
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-xl">
        <form @submit.prevent="updateProduct">
          <div class="mb-4">
            <label for="productName" class="block text-sm mb-1">Product Name:</label>
            <input type="text" id="productName" v-model="product.name" class="w-full h-10 px-2 border rounded" required>
          </div>
  
          <div class="mb-4">
            <label for="productPrice" class="block text-sm mb-1">Price:</label>
            <input type="number" id="productPrice" v-model.number="product.price" class="w-full h-10 px-2 border rounded" required>
          </div>
  
          <div class="mb-4">
            <label for="productAmount" class="block text-sm mb-1">Amount:</label>
            <input type="number" id="productAmount" v-model.number="product.amount" class="w-full h-10 px-2 border rounded" required>
          </div>
  
          <div class="mb-4">
            <label for="productDescription" class="block text-sm mb-1">Description:</label>
            <textarea id="productDescription" v-model="product.description" class="w-full px-2 border rounded" rows="4"></textarea>
          </div>
  
          <CustomButton type="submit" customButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Update Product</CustomButton>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import CustomButton from '@/components/atoms/Button.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import { useCompleteStore } from "@/store/store";
  
  export default {
    components: {
      CustomButton,
      NormalHeading
    },
    data() {
      return {
        product: {
          id: null,
          name: '',
          price: 0,
          amount: 0,
          description: ''
        },
        store: useCompleteStore()
      };
    },
    methods: {
      async updateProduct() {
        // TODO: Implement the logic to update the product
        console.log(`Updating product: ${this.product.id}`);
        // Redirect to products list or show a success message
      }
    },
    async mounted() {
      const productId = parseInt(this.$route.params.productId);
      this.product = this.store.getProductById(productId);
    }
  };
  </script>
  