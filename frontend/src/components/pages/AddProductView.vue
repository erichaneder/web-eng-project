<template>
    <div class="min-h-screen flex flex-col items-center p-5 mt-12">
      <div class="bg-white p-8 rounded-lg shadow-md w-96">
        <NormalHeading text="Admin: Add Product" />
  
        <form @submit.prevent="addProduct">
          <div class="mb-4">
            <label for="productName" class="block text-lg mb-1 text-gray-600">Product Name:</label>
            <input v-model="product.name" type="text" id="productName" required placeholder="Product name" class="w-full h-10 px-2 border rounded">
          </div>
          <div class="mb-4">
            <label for="productDescription" class="block text-lg mb-1 text-gray-600">Description:</label>
            <textarea v-model="product.description" id="productDescription" required placeholder="Product description" class="w-full h-32 px-2 py-2 border rounded"></textarea>
          </div>
          <div class="mb-4">
            <label for="productPrice" class="block text-lg mb-1 text-gray-600">Price:</label>
            <input v-model="product.price" type="number" id="productPrice" required placeholder="Product price" class="w-full h-10 px-2 border rounded">
          </div>
          
          <div class="mb-4">
            <label for="productImage" class="block text-lg mb-1 text-gray-600">Product Image:</label>
            <input type="file" ref="productImage" id="productImage" class="w-full p-2 border rounded" @change="onImageChange">
          </div>

          <!-- add more fields like product image, sizes, colors, etc. -->
          <CustomButton type="submit" customButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Add Product</CustomButton>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import CustomButton from '@/components/atoms/Button.vue';  
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  
  export default {
    name: 'AdminAddProduct',
    components: {
      NormalHeading,
      CustomButton
    },
    data() {
      return {
        product: {
          name: '',
          description: '',
          price: 0,
          image: null
          // add other fields like product image, sizes, colors, etc.
        }
      }
    },
    methods: {
      addProduct() {
        // product addition logic here -> ajax call to backend to insert a new product to the db
        console.log(this.product);
        this.resetForm();
      },
      resetForm() {
        this.product = {
          name: '',
          description: '',
          price: 0
        };
        if (this.$refs.productImage) {
            this.$refs.productImage.value = '';
        }
      },
      onImageChange(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
            this.product.image = e.target.result;
            };
            reader.readAsDataURL(file);
        }
      }
    }
  }
  </script>
  