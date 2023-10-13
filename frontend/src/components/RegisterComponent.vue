<template>
    <div class="min-h-screen flex items-center justify-center bg-gray-100">
      <div class="w-96 bg-white p-8 rounded shadow">
        <NormalHeading text="Register" />
        <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
        <FormComponent>
          <button type="submit" class="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Register</button>
        </FormComponent>
      </div>
    </div>
  </template>
  
  <script>
  import FormComponent from "@/components/molecules/FormComponent.vue";
import NormalHeading from "./atoms/NormalHeading.vue";
  
  export default {
    components: {
    FormComponent,
    NormalHeading
}
  }
  </script>
  