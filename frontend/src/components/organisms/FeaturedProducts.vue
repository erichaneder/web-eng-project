<template>
  <div class="grid grid-cols-1 md:grid-cols-3 gap-6 w-full">
    <ProductDisplay
      v-for="product in products"
      :key="product.id"
      :product="product"
      @goToProductDetail="goToProductDetail"
    />
  </div>
</template>

<script>
import ProductDisplay from "@/components/molecules/ProductDisplay.vue";

import green from '@/assets/green1.webp';
import blue from '@/assets/blue1.webp';
import retro from '@/assets/retro.webp';

export default {
  name: "FeaturedProducts",
  components: {
    ProductDisplay,
  },
  methods: {
    goToProductDetail(productId) {
      this.$router.push({ path: `/product/${productId}` });
    },
  },
  data() {
    return {
      products: [
        {
          id: 1,
          name: "Nike Dunk Low Sneakers - Olive",
          image: green,
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 129.99,
        },
        {
          id: 2,
          name: "Nike Dunk Low Sneakers - Blue",
          image: blue,
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 119.99,
        },
        {
          id: 3,
          name: "Nike Dunk Low Sneakers - Retro",
          image: retro,
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 165.99,
        },
      ],
    };
  },
};
</script>
