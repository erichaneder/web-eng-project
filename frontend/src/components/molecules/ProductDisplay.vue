<template>
    <div class="bg-white p-5 rounded-lg shadow-md transform transition-transform duration-300 hover:scale-105 hover:bg-gray-50 hover:shadow-lg">
        <!-- Lazy load image implemented to lower LCP load time, but doesn't seem to do much -->    
        <div class="w-full h-52 bg-white rounded mb-4 flex items-center justify-center">
            <img v-lazy="product.image" :alt="product.name" class="object-cover rounded" />
        </div>
        <h3 class="text-lg font-semibold text-gray-700 mb-2">{{ product.name }}</h3>
        <p class="text-gray-600 mb-2">{{ product.description }}</p>
        <p class="text-pink-800 font-bold">
            <s class="text-black">€ 199.99 </s>
            &nbsp; € {{ product.price }}
        </p>
        <CustomButton @clicked="goToProductDetail(product.id)">
            <div class="flex flex-col items-center">
                <ChevronDoubleRightIcon class="h-6 w-6" />
            </div>
        </CustomButton>
    </div>
</template>
  
<script>
import { ChevronDoubleRightIcon } from '@heroicons/vue/24/outline';
import CustomButton from '@/components/atoms/Button.vue';

export default {
    name: 'ProductDisplay',
    components: {
        ChevronDoubleRightIcon,
        CustomButton,
    },
    props: {
        product: {
            type: Object,
            required: true,
        },
    },
    methods: {
        goToProductDetail(productId) {
            // Logic to navigate to product detail
            this.$router.push(`/product/${productId}`);
        },
    },
};
</script>