<template>
    <div class="min-h-screen flex flex-col items-center bg-gray-100 p-5">
      <div class="bg-white p-8 rounded-lg shadow-md w-full w-full">
        <NormalHeading text="All Products" />
        <!-- Search Field -->
        <div class="mb-4">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="Search products..." 
            class="w-full h-10 px-4 mb-2 border rounded"
          />
        </div>
        <!-- Product List -->
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 w-full">
          <ProductTile v-for="product in filteredProducts" :key="product.id" :product="product" @goToProductDetail="goToProductDetail" />
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
        store: useCompleteStore(),
        searchQuery: ''
      };
    },
    computed: {
      filteredProducts() {
        if (!this.searchQuery) {
          return this.products;
        }
        const searchLower = this.searchQuery.toLowerCase();
        return this.products.filter(product => {
          return Object.values(product).some(value =>
            String(value).toLowerCase().includes(searchLower)
          );
        });
      }
    },
    mounted() {
      this.products = this.store.getAllProducts; 
    }
  };
  </script>