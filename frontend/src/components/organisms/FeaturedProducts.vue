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
          image: require("@/assets/nike_red2.jpg"),
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 120,
        },
        {
          id: 2,
          name: "Nike Dunk Low Sneakers - Blue",
          image: require("@/assets/nike_blue2.jpg"),
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 120,
        },
        {
          id: 3,
          name: "Nike Dunk Low Sneakers - Green",
          image: require("@/assets/nike_green.jpg"),
          description:
            "Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.",
          price: 120,
        },
        // Additional stuff / product objects..
      ],
    };
  },
};
</script>
