<template>
    <div class="grid grid-cols-1 md:grid-cols-3 gap-6 w-full">
        <div v-for="product in products" :key="product.id" class="bg-white p-5 rounded-lg shadow-md transform transition-transform duration-300 hover:scale-105 hover:bg-gray-50 hover:shadow-lg">
            <img :src="product.image" :alt="product.name" class="w-full h-52 object-cover rounded mb-4"/>
            <h3 class="text-lg font-semibold text-gray-700 mb-2">{{ product.name }}</h3>
            <p class="text-gray-600 mb-2">{{ product.description }}</p>
            <p class="text-pink-800 font-bold">${{ product.price }}</p>
            <button @click="goToProductDetail(product.id)" type="submit" class="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500 mt-3"><div class="flex flex-col items-center"> <ChevronDoubleRightIcon class="h-6 w-6" /></div></button>
        </div>
    </div>
</template>

<script>
    import { ChevronDoubleRightIcon } from '@heroicons/vue/24/outline';

    export default {
        name: "FeaturedProducts",
        components: {
            ChevronDoubleRightIcon
        },
        methods: {
            goToProductDetail(productId) {
                this.$router.push({ path: `/product/${productId}` });
            }
        },
        data() {
            return {
                products: [
                    { id: 1, name: 'Nike Dunk Low Sneakers - Red', image: require('@/assets/nike_red2.jpg'), description: 'Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.', price: 120 },
                    { id: 2, name: 'Nike Dunk Low Sneakers - Blue', image: require('@/assets/nike_blue2.jpg'), description: 'Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.', price: 120 },
                    { id: 3, name: 'Nike Dunk Low Sneakers - Green', image: require('@/assets/nike_green.jpg'), description: 'Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.', price: 120 },
                    // Additional product objects...
                ]
            }
        }
    };
</script>