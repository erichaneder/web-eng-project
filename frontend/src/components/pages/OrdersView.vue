<template>
    <div class="container mx-auto my-8 px-4 mb-40">
      <NormalHeading text="All Orders"/>
      <div class="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
        <table class="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
          <thead>
            <tr class="text-center">
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">
                ID
              </th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">
                OrderNo
              </th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">
                UserId
              </th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">
                Total Amount
              </th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">
                Total Quantity
              </th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">
                Actions
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="order in orders" :key="order.id" class="hover:bg-gray-100">
              <td class="py-2 px-3 border-b border-gray-200">{{ order.id }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.orderNo }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.userId }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.totalAmount }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ order.totalQuantity }}</td>
              <td class="py-2 px-3 border-b border-gray-200">
                <CustomButton @click="editOrder(order.id)" customButtonStyle="p-2 hover:bg-green-500">
                    <PencilIcon class="h-5 w-5 text-green-400 hover:text-white" />
                </CustomButton>
                <CustomButton @click="deleteOrder(order.id)" customButtonStyle="p-2 hover:bg-red-500">
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
  import axios from 'axios';
  import { TrashIcon, PencilIcon } from '@heroicons/vue/24/outline'
  import CustomButton from '@/components/atoms/Button.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  
  export default {
    components: {
        TrashIcon,
        CustomButton,
        PencilIcon,
        NormalHeading
    },
    data() {
      return {
        orders: []
      };
    },
    methods: {
        async deleteOrder(orderId) {
            console.log("test " + orderId);
            try {
                const response = await axios.delete('http://localhost:8080/api/v1/order/delete/' + orderId); 
                //logic here when delete successful
                console.log("Response: " + response);
            } catch (error) {
                console.error('Error deleting orders:', error);
            }
        },
        editOrder(orderId) {
            this.$router.push({ path: '/order/' + orderId });
        }
    },
    async created() {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/order/list/'); 
        this.orders = response.data;
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    }
  };
  </script>
  