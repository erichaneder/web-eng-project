<template>
    <div class="min-h-screen py-10 px-5 flex flex-col items-center">
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
        <NormalHeading text="Order Detail: "/>
  
        <div v-if="order" class="mt-6">
          <p class="text-gray-700"><strong>Order Number:</strong> {{ order.orderNo }}</p>
          <p class="text-gray-700"><strong>Customer: </strong> {{ order.user.name }}</p>
          <p class="text-gray-700"><strong>Orderdate:</strong> {{ order.upload_date }}</p>
          <p class="text-gray-700"><strong>Total Amount:</strong> ${{ order.totalAmount }}</p>
          <p class="text-gray-700"><strong>Total Quantity:</strong> {{ order.totalQuantity}}</p>
  
          <div class="mt-4">
            <h3 class="text-lg font-semibold text-gray-800 mb-2">Products:</h3>
            <ul>
              <li v-for="product in order.orderDetails" :key="product.id" class="mb-2">
                <span class="text-gray-600">{{ product.product.name }} - ${{ product.product.price }} x {{ product.quantity }}</span>
              </li>
            </ul>
          </div>
        </div>
  
        <div v-else class="text-center py-10">
          <p>Loading order details...</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
    import NormalHeading from "@/components/atoms/NormalHeading.vue";
    import { useCompleteStore } from "@/store/store";
  
    export default {
      components: {
        NormalHeading,
      },
      data() {
        return {
          store: useCompleteStore(),
          order: null  
        };
      },
      async mounted() {
        console.log("getting order...");
        const orderId = parseInt(this.$route.params.id);
        console.log("orderid: " + orderId);
        this.order = await this.store.getOrderById(orderId);
      }
    }
    </script>
    