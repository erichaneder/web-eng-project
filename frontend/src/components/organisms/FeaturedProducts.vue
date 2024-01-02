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
          name: "Nike Dunk Low Sneakers - Red",
          image: require("@/assets/red1.jpg"),
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 119.99,
        },
        {
          id: 2,
          name: "Nike Dunk Low Sneakers - Blue",
          image: require("@/assets/blue1.jpg"),
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 119.99,
        },
        {
          id: 3,
          name: "Nike Dunk Low Sneakers - Olive",
          image: require("@/assets/green1.jpg"),
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 99.99,
        },
        // Additional stuff / product objects..
      ],
    };
  },
};
</script>
