<template>
    <div class="min-h-screen flex flex-col items-center p-5 mt-12">

      <!-- Success Alert -->
      <div v-if="showAlert" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-8" role="alert">
        <strong class="font-bold">Success!</strong>
        <span class="block sm:inline">Product successfully added!</span>
      </div>

      <div class="bg-white p-8 rounded-lg shadow-md w-96">
        <NormalHeading text="Add Product" />
  
        <form @submit.prevent="addProduct">
          <div class="mb-4">
            <label for="productName" class="block text-lg mb-1 text-gray-600">Product Name:</label>
            <input v-model="form.values.name" @blur="validate('name')" type="text" id="productName" placeholder="Product name" class="w-full h-10 px-2 border rounded">
            <p class="text-red-500" v-if="form.errors.name">{{ form.errors.name }}</p>
          </div>
          <div class="mb-4">
            <label for="productDescription" class="block text-lg mb-1 text-gray-600">Description:</label>
            <textarea v-model="form.values.description" @blur="validate('description')" id="productDescription" placeholder="Product description" class="w-full h-32 px-2 py-2 border rounded"></textarea>
            <p class="text-red-500" v-if="form.errors.description">{{ form.errors.description }}</p>
          </div>
          <div class="mb-4">
            <label for="productPrice" class="block text-lg mb-1 text-gray-600">Price:</label>
            <input v-model="form.values.price" @blur="validate('price')" type="number" id="productPrice" step="0.01" placeholder="Product price" class="w-full h-10 px-2 border rounded">
            <p class="text-red-500" v-if="form.errors.price">{{ form.errors.price }}</p>
          </div>
          
          <div class="mb-4">
            <label for="productImage" class="block text-lg mb-1 text-gray-600">Product Image:</label>
            <input type="file" ref="productImage" id="productImage" class="w-full p-2 border rounded" @change="onImageChange">
            <p class="text-red-500" v-if="form.errors.image">{{ form.errors.image }}</p>
          </div>

          <CustomButton type="submit" customButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Add Product</CustomButton>
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
  import { useCompleteStore } from '@/store/store';
  import { object, string, number } from "yup";

  const productSchema = object().shape({
    name: string().required("Product name is required"),
    description: string().required("Product description is required"),
    price: number().typeError("Price must be a number").min(0, "Price must be a positive number").required("Price is required"),
    image: string().required("Image is required"),
  });

  export default {
    name: 'AdminAddProduct',
    components: {
      NormalHeading,
      CustomButton,
      ErrorModal
    },
    data() {
      return {
        form: {
          values: {
            name: '',
            description: '',
            price: 0,
            image: null
          },
          errors: {
            name: '',
            description: '',
            price: '',
            image: ''
          }
        },
        showAlert: false,
        isErrorModalVisible: false,
        errorMessage: "",
        store: useCompleteStore(),
      }
    },
    methods: {
      async addProduct() {
        productSchema.validate(this.form.values, { abortEarly: false })
        .then(async () => {
          // Clear previous errors
          this.form.errors = { name: '', description: '', price: '', image: '' };

          const payload = {
            id: this.form.values.id,
            name: this.form.values.name,
            price: this.form.values.price,
            description: this.form.values.description,
            image: this.form.values.image
          };
          const success = await this.store.addProduct(payload);
          if(success) {
            this.showAlert = true;
            setTimeout(this.resetForm, 2000);
          } else {
            this.errorMessage = "Error adding product!";
            this.isErrorModalVisible = true;
          }
        }).catch((err) => {
          console.log(err);
          this.errorMessage = "Error adding product: " + err;
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
    },
    resetForm() {
        this.form.values = { name: '', description: '', price: 0, image: null };
        this.form.errors = { name: '', description: '', price: '', image: '' };
        this.showAlert = false;
        if (this.$refs.productImage) {
          this.$refs.productImage.value = '';
        }
      },
      onImageChange(e) {
        console.log("Image change: " + e);
        //const file = e.target.files[0];
        //wait for backend to finish file upload
        this.form.values.image = "blue1.webp"; //default
        
      }
    }
  }
  </script>
  