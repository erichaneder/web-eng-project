<template>
    <div class="min-h-screen flex items-center justify-center bg-gray-100">
      <div class="w-96 bg-white p-8 rounded shadow">
        <h1 class="text-pink-800 font-bold font-sans text-4xl text-center">Register</h1>
        <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
        <FormComponent>
          <button type="submit" class="w-full bg-pink-800 text-white p-2 rounded hover:bg-red-400">Register</button>
        </FormComponent>
      </div>
    </div>
  </template>
  
  <script>
  import FormComponent from "@/components/molecules/FormComponent.vue";
  
  export default {
    components: {
      FormComponent
    }
  }
  </script>
  