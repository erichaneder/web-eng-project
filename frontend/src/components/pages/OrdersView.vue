<template>
    <div class="container mx-auto my-8 px-4 mb-40">
      <NormalHeading text="All Orders"/>
      <div class="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
        <table class="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
          <thead>
            <tr class="text-center">
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">ID</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">OrderNo</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">UserId</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Total Amount</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Total Quantity</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="order in orders" :key="order.id" class="hover:bg-gray-100">
              <td class="py-2 px-3 border-b border-gray-200">{{ order.id }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.orderNo }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.user.id }}</td>
              <td class="py-2 px-3 border-b border-gray-200">€ {{ order.totalAmount }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.totalQuantity }}</td>
              <td class="py-2 px-3 border-b border-gray-200">
                <CustomButton @clicked="editOrder(order.id)" customButtonStyle="p-2 hover:bg-green-500">
                    <EyeIcon class="h-5 w-5 text-green-400 hover:text-white" />
                </CustomButton>
                <CustomButton @clicked="deleteOrder(order.id)" customButtonStyle="p-2 hover:bg-red-500">
                    <TrashIcon class="h-5 w-5 text-red-400 hover:text-white" />
                </CustomButton>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import { TrashIcon, EyeIcon } from '@heroicons/vue/24/outline'
  import CustomButton from '@/components/atoms/Button.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import { useCompleteStore } from "@/store/store";
  
  export default {
    components: {
        TrashIcon,
        CustomButton,
        EyeIcon,
        NormalHeading
    },
    data() {
      return {
        orders: [],
        store: useCompleteStore()
      };
    },
    methods: {
        async deleteOrder(orderId) {
          await this.store.deleteOrder(orderId);
          console.log(`Done deleting order with id: ${orderId}, now refreshing ...`);
          await this.store.fetchOrders();
          this.orders = this.store.getAllOrders;
        },
        editOrder(orderId) {
            this.$router.push({ path: '/order/' + orderId });
        }
    },
    async mounted() {
      await this.store.fetchOrders();
      this.orders = this.store.getAllOrders; 
    }
  };
  </script>
  