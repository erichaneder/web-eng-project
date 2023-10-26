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
          // Send formData to backend using fetch 
          const response = await fetch("/api/register", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
          });
          
          // Check if the response is successful
          if (response.ok) {
            console.log("Registration was successful!");
            const responseData = await response.json();
            console.log("Response from backend:", responseData);
            
            const token = response.token;
            localStorage.setItem('token', token);

            // Redirect to the home page or any other route if needed
            this.$router.push({ path: '/' });
          } else {
            const errorData = await response.json();
            console.error("Error registering user:", errorData.message);
          }
        } catch (error) {
            console.error("Network error: "+ error);
        }
      }
    },
  }
  </script>
  