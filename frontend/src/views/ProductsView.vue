<template>
    <div class="min-h-screen flex flex-col items-center bg-gray-100 p-5">
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-6xl">
        <NormalHeading text="All Products" />
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 w-full">
          <div 
            v-for="product in products" 
            :key="product.id" 
            class="bg-white p-5 rounded-lg shadow-md transform transition-transform duration-300 hover:scale-105 hover:bg-gray-50 hover:shadow-lg"
          >
            <img :src="product.image" :alt="product.name" class="w-full h-52 object-cover rounded mb-4" />
            <h3 class="text-lg font-semibold text-gray-700 mb-2">{{ product.name }}</h3>
            <p class="text-gray-600 mb-2">{{ product.description }}</p>
            <p class="text-pink-800 font-bold">${{ product.price }}</p>
            <button @click="goToProductDetail(product.id)" type="submit" class="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mt-3">View Details</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  
  export default {
    components: {
      NormalHeading
    },
    methods: {
        goToProductDetail(productId) {
                this.$router.push({ path: `/product/${productId}` });
            }
    },
    data() {
      return {
        products: [
          { id: 1, name: 'Nike Dunk Low Sneakers - Red', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 2, name: 'Nike Dunk Low Sneakers - Blue', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 3, name: 'Nike Dunk Low Sneakers - Green', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 4, name: 'Nike Dunk Low Sneakers - Red', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 5, name: 'Nike Dunk Low Sneakers - Blue', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 6, name: 'Nike Dunk Low Sneakers - Green', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 7, name: 'Nike Dunk Low Sneakers - Red', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          { id: 8, name: 'Nike Dunk Low Sneakers - Blue', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable.', price: 120 },
          // ... other products ...
        ],
      };
    },
  };
  </script>