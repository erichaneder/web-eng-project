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

  export default {
    components: {
      NormalHeading,
      ProductTile
    },
    methods: {
        goToProductDetail(productId) {
                this.$router.push({ path: `/product/${productId}` });
            },
        async fetchProducts() {
          try {
            const response = await axios.get('http://localhost:8080/api/v1/product/list/');
            this.products = response.data.map(product => {
              return {
                id: product.id,
                name: product.name,
                description: product.description,
                price: product.price,
                image: require(`@/assets/${product.image}`)
              };
            });
          } catch (error) {
            console.error('Error fetching products:', error);
          }
        }
    },
    data() {
      return {
        products: []
      };
    },
    mounted() {
      this.fetchProducts();
    }
  };
  </script>