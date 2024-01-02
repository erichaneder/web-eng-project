<template>
    <div class="min-h-screen flex flex-col items-center bg-gray-100 p-5">
      <div class="bg-white p-8 rounded-lg shadow-md w-full w-full">
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
  import { useCompleteStore } from '@/store/store';

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
        products: [],
        store: useCompleteStore()
      };
    },
    mounted() {
      this.products = this.store.getAllProducts; 
    }
  };
  </script>