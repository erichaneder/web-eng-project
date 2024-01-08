<template>
    <div class="container mx-auto my-8 px-4 mb-40">
      <NormalHeading text="All Products"/>
  
      <div class="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
        <table class="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
          <thead>
            <tr class="text-center">
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">ID</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Name</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Price</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Amount</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="product in products" :key="product.id" class="hover:bg-gray-100">
              <td class="py-2 px-3 border-b border-gray-200">{{ product.id }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ product.name }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ product.price }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ product.amount }}</td>
              <td class="py-2 px-3 border-b border-gray-200">
                <CustomButton @clicked="editProduct(product.id)" customButtonStyle="p-2 hover:bg-green-500">
                  <PencilIcon class="h-5 w-5 text-green-400 hover:text-white" />
                </CustomButton>
                <CustomButton @clicked="deleteProduct(product.id)" customButtonStyle="p-2 hover:bg-red-500">
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
import { TrashIcon, PencilIcon } from '@heroicons/vue/24/outline'
import CustomButton from '@/components/atoms/Button.vue';
import NormalHeading from "@/components/atoms/NormalHeading.vue";
import { useCompleteStore } from "@/store/store";

export default {
  components: {
    TrashIcon,
    CustomButton,
    PencilIcon,
    NormalHeading
  },
  data() {
    return {
      products: [],
      store: useCompleteStore()
    };
  },
  methods: {
    async deleteProduct(productId) {
      await this.store.deleteProduct(productId);
      await this.store.fetchProducts();
      this.products = this.store.getAllProducts;
    },
    editProduct(productId) {
      // Navigate to product edit page
      this.$router.push({ path: '/product/edit/' + productId });
    }
  },
  async mounted() {
    // Fetch products and assign them to the products data property
    this.products = this.store.getAllProducts; 
  }
};
</script>
