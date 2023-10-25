<template>
    <div class="min-h-screen flex flex-col items-center">
      <div class="w-96 bg-white p-8 rounded shadow mt-20">
        <NormalHeading text="Register" />
        <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
        <FormComponent @formSubmit="submit">
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
    },
    methods: {
      async submit(formData) {

        try {   
          const data = formData.name;
          console.log("Name in form: " + data)
          // Send formData to backend using fetch or axios.
          // If successful:
          this.$router.push({ path: '/' });
        } catch (error) {
            console.error("Error registering user:", error);
        }
      }
    },
  }
  </script>
  