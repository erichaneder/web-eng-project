<template>
  <div class="bg-white p-5 rounded-lg shadow-md transform transition duration-300 hover:scale-105 hover:bg-gray-50 hover:shadow-lg">
    <img :src="product.image" :alt="product.name" class="w-full h-52 object-cover rounded mb-4" />
    <h3 class="text-lg font-semibold text-gray-700 mb-2">{{ product.name }}</h3>
    <p class="text-gray-600 mb-2">{{ product.description }}</p>
    <p class="text-pink-800 font-bold">${{ product.price }}</p>
    <CustomButton @clicked="goToProductDetail" customButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mt-3">View Details</CustomButton>
  </div>
</template>

<script>
import CustomButton from '@/components/atoms/Button.vue';

export default {
  components: {
    CustomButton,
  },
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
  methods: {
    goToProductDetail() {
      this.$emit('goToProductDetail', this.product.id);
    },
  },
};
</script>