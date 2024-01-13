<template>
    <div class="min-h-screen flex flex-col items-center px-5 pt-20">

       <!-- Success Alert -->
        <div v-if="showAlert" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-8" role="alert">
            <strong class="font-bold">Success!</strong>
            <span class="block sm:inline">Order successfully sent!</span>
        </div>

        <div class="w-xl bg-white p-8 rounded shadow">
        <NormalHeading text="Basket" />
        <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
        
        <!-- Basket Items -->
        <div v-if="basketItems.length">
            <div v-for="item in basketItems" :key="item.id" class="flex items-center mb-4">
                <img :src="item.image" :alt="item.name" class="w-40 h-40 object-contain mr-4">
                <div>
                    <h2 class="text-lg font-bold">{{ item.name }}</h2>
                    <br>
                    <p class="text-gray-600">{{ item.description }}</p>
                    <p class="text-teal-700 mt-2">{{ item.quantity }} x {{ item.price }} €</p>
                </div>
                <CustomButton @clicked="increaseQuantity(item.id)" customButtonStyle="p-2 hover:bg-green-500">
                    <PlusIcon class="h-5 w-5 text-green-400 hover:text-white" />
                </CustomButton>
                <CustomButton @clicked="removeItem(item.id)" customButtonStyle="p-2 hover:bg-red-500">
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
            <p class="text-xl">Total: {{ this.store.getBasketTotal }} €</p>
            <CustomButton @clicked="orderBasket()" customButtonStyle="mt-4 w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Order Basket</CustomButton>
        </div>
        </div>
    </div>
</template>

<script>
    import CustomButton from '@/components/atoms/Button.vue';
    import NormalHeading from "@/components/atoms/NormalHeading.vue";
    import { TrashIcon, PlusIcon } from '@heroicons/vue/24/outline';
    import { useCompleteStore } from '@/store/store';

    export default {
    components: {
        NormalHeading,
        TrashIcon,
        CustomButton,
        PlusIcon
    },
    data() {
        return {
            basketItems: [],
            store: useCompleteStore(),
            showAlert: false
        }
    },
    methods: {
        removeItem(itemId) {
            this.store.removeFromBasket(itemId);
        },
        increaseQuantity(itemId) {
            this.store.increaseQuantity(itemId);
        },
        async orderBasket() {
            if(this.store.isLoggedIn) {
                await this.store.orderBasket();
                //set alert for 2 seconds
                this.showAlert = true;
                setTimeout(this.goToHome, 2000);    
            } else {
                this.$router.push({ path: '/login' });
            }
        },
        goToHome() {
            this.$router.push({ path: '/' });
        }
    },
    mounted() {
        this.basketItems = this.store.getBasketItems;
    }
}
</script>
  