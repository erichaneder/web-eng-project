<template>
    <form @submit.prevent="submit">
      <InputField v-model="formData.name" id="name" label="Name" :errors="fieldErrors.name" type="text" placeholder="Your Name"/>
      <InputField v-model="formData.address" id="address" label="Address" :errors="fieldErrors.address" type="text" placeholder="Your Address"/>
      <div class="flex space-x-4"> <!-- space-x-4 provides spacing between flex children -->
        <InputField v-model="formData.city" id="city" :errors="fieldErrors.city" label="City" type="text" placeholder="Your City"/>
        <InputField v-model="formData.zip" id="zip" :errors="fieldErrors.zip" label="Zip" type="text" placeholder="Your Zip"/>
      </div>
      <InputField v-model="formData.country" id="country" :errors="fieldErrors.country" label="Country" type="text" placeholder="Your Country"/>
      <InputField v-model="formData.email" id="email" :errors="fieldErrors.email" label="Email" type="email" placeholder="Your Email"/>
      <InputField v-model="formData.password" id="password" :errors="fieldErrors.password" label="Password" type="password" placeholder="Your Password"/>
      <slot></slot> <!-- slot for submit button -->
    </form>
  </template>
  
  <script>
  import { ref } from 'vue';

  import InputField from "@/components/atoms/InputField.vue";

  export default {
    components: {
      InputField
    },
    setup(props, context) {
        const formData = ref({
            name: '',
            address: '',
            city: '',
            zip: '',
            country: '',
            email: '',
            password: ''
        });
        const fieldErrors = ref({
          name: null,
          address: null,
          city: null,
          zip: null,
          country: null,
          email: null,
          password: null
      });

        function submit() {
          const hasError = Object.values(fieldErrors.value).some(error => error);
          if (!hasError) {
              console.log("Successful validation!");
              console.log('Emitting form data:', formData.value);
              context.emit('formSubmit', formData.value);
          } else {
              console.log("Validation failed!");
          } 
        }

        return {
            formData,
            fieldErrors,
            submit
        };
    }
  }
  </script>
  