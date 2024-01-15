<template>
    <div class="container mx-auto my-8 px-4 mb-40 flex flex-col items-center">

      <!-- Success Alert -->
      <div v-if="showAlert" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-8" role="alert">
        <strong class="font-bold">Success!</strong>
        <span class="block sm:inline">Product successfully added!</span>
      </div>
      
      <NormalHeading text="Edit Product"/>
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-xl">
        <form @submit.prevent="updateProduct">
          <div class="mb-4">
            <label for="productName" class="block text-sm mb-1">Product Name:</label>
            <input type="text" id="productName" v-model="form.values.name" @blur="validate('name')" class="w-full h-10 px-2 border rounded">
            <p class="text-red-500" v-if="form.errors.name">{{ form.errors.name }}</p>
          </div>
  
          <div class="mb-4">
            <label for="productPrice" class="block text-sm mb-1">Price:</label>
            <input type="number" id="productPrice" step="0.01" v-model.number="form.values.price" @blur="validate('price')" class="w-full h-10 px-2 border rounded">
            <p class="text-red-500" v-if="form.errors.price">{{ form.errors.price }}</p>
          </div>
  
          <div class="mb-4">
            <label for="productDescription" class="block text-sm mb-1">Description:</label>
            <textarea id="productDescription" v-model="form.values.description" @blur="validate('description')" class="w-full px-2 border rounded" rows="4"></textarea>
            <p class="text-red-500" v-if="form.errors.description">{{ form.errors.description }}</p>
          </div>
  
          <CustomButton type="submit" customButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Update Product</CustomButton>
        </form>
      </div>
    </div>

    <ErrorModal
    :isVisible="isErrorModalVisible"
    :errorMessage="errorMessage"
    @update:isVisible="isErrorModalVisible = $event"
    />
  </template>
  
  <script>
  import CustomButton from '@/components/atoms/Button.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import ErrorModal from "@/components/atoms/ErrorModal.vue";
  import { useCompleteStore } from "@/store/store";
  import { object, string, number } from "yup";

  const productSchema = object().shape({
    name: string().required("Product name is required"),
    price: number().positive("Price must be a positive number").required("Price is required"),
    description: string().required("Product description is required"),
  });

  export default {
    components: {
      CustomButton,
      NormalHeading,
      ErrorModal
    },
    data() {
      return {
        form: {
          values: {
            id: null,
            name: '',
            price: 0,
            description: ''
          },
          errors: {
            name: '',
            price: '',
            description: ''
          }
      },
        store: useCompleteStore(),
        isErrorModalVisible: false,
        errorMessage: "",
        showAlert: false,
      };
    },
    methods: {
      async updateProduct() {
        productSchema.validate(this.form.values, { abortEarly: false })
          .then(async () => {
            this.form.errors = { name: '', price: '', description: '' };
            const success = await this.store.updateProduct(this.form.values);
            if(success) {
              this.showAlert = true;
              setTimeout(this.resetForm, 2000);
            } else {
              this.errorMessage = "Error adding product!";
              this.isErrorModalVisible = true;
            }
          })
          .catch((err) => {
            console.log(err);
            this.errorMessage = "Error updating product: " + err;
            this.isErrorModalVisible = true;
          });
      },
      validate(field) {
        productSchema
          .validateAt(field, this.form.values)
          .then(() => {
            this.form.errors[field] = "";
          })
          .catch((err) => {
            this.form.errors[field] = err.message;
          });
      }
    },
    async mounted() {
      const productId = parseInt(this.$route.params.productId);
      const productData = this.store.getProductById(productId);
      if (productData) {
        this.form.values = { ...productData };
      } else {
        console.error("Product not found");
        // Handle product not found case
      }
    }
  };
  </script>
  