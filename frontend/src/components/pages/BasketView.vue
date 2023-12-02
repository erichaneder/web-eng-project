<template>
    <div class="min-h-screen flex flex-col items-center px-5 pt-20">
        <div class="w-xl bg-white p-8 rounded shadow">
        <NormalHeading text="Basket" />
        <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
        
        <!-- Basket Items -->
        <div v-if="basketItems.length">
            <div v-for="item in basketItems" :key="item.id" class="flex items-center mb-4">
                <img :src="item.image" alt="Item image" class="w-40 h-40 object-cover mr-4">
                <div>
                    <h2 class="text-lg font-bold">{{ item.productName }}</h2>
                    <br>
                    <p class="text-gray-600">{{ item.productDescription }}</p>
                    <p class="text-teal-700 mt-2">${{ item.price }}</p>
                </div>
                <CustomButton @click="removeItem(item.id)" customButtonStyle="p-2 hover:bg-red-500">
                    <TrashIcon class="h-5 w-5 text-red-400 hover:text-white"/>
                </CustomButton>
            </div>
        </div>
        
        <!-- Empty Basket Message -->
        <div v-else class="text-center py-10">
            <p>Your basket is empty.</p>
        </div>
        
        <!-- Basket Total -->
        <div v-if="basketItems.length" class="mt-4 text-right">
            <p class="text-xl">Total: ${{ basketTotal }}</p>
            <CustomButton @click="orderBasket()" customButtonStyle="mt-4 w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Proceed to Checkout</CustomButton>
        </div>
        </div>
    </div>
</template>

<script>
    import CustomButton from '@/components/atoms/Button.vue';
    import NormalHeading from "@/components/atoms/NormalHeading.vue";
    import { TrashIcon } from '@heroicons/vue/24/outline';
    import axios from 'axios';
    import { mapGetters, mapActions } from 'vuex';

    export default {
    components: {
        NormalHeading,
        TrashIcon,
        CustomButton
    },
    computed: {
        ...mapGetters(['basketTotal']),
        basketItems() {
            return this.$store.state.basketItems;
        }
    },
    methods: {
        ...mapActions(['removeFromBasket']),
        removeItem(itemId) {
            this.removeFromBasket(itemId);
        },
        async orderBasket() {
            console.log("test ");
            const payload = {
                orderNo: "RANDOM_ORDERNO_2423423",
                totalAmount: this.getBasketTotal(),
                userId: localStorage.getItem("userId"),
            };
            try {
                const response = await axios.post('http://localhost:8080/api/v1/order/add/', payload); 
                //logic here when ordering basket successful
                console.log("Response: " + response.data);
            } catch (error) {
                console.error('Error ordering basket:', error);
            }
        }
    },
    }
</script>
  