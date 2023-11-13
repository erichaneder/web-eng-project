<template>
    <div class="min-h-screen flex flex-col items-center bg-gray-100 p-5">
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-6xl">
        <NormalHeading text="All Products" />
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 w-full">
          <ProductTile v-for="product in products" :key="product.id" :product="product" @goToProductDetail="goToProductDetail" />
        </div>
      </div>
    </div>
</template>
  
  <script>
  import ProductTile from '@/components/molecules/ProductTile.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import axios from 'axios';

  async function fetchData() {
    return axios.get('https://jsonplaceholder.typicode.com/posts/')
    .then((response) => response.data)
    .catch(function (error) {
      console.log(error);
    });
  }

  export default {
    components: {
      NormalHeading,
      ProductTile
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
    beforeCreate: async function() {
      const posts = await fetchData();
      //this.posts = posts;
      console.log('posts');
      console.log(posts);
    },
  };
  </script>