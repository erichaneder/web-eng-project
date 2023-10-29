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

    <ErrorModal 
      :isVisible="isErrorModalVisible" 
      :errorMessage="errorMessage"
      @update:isVisible="isErrorModalVisible = $event"
    />
  </template>
  
  <script>
  import FormComponent from "@/components/molecules/FormComponent.vue";
  import NormalHeading from "./atoms/NormalHeading.vue";
  import ErrorModal from '../components/ErrorModal.vue'
  
  export default {
    components: {
      FormComponent,
      NormalHeading,
      ErrorModal
    },
    data() {
      return {
        isErrorModalVisible: false,
        errorMessage: ''
      }
    },
    methods: {
      async submit(formData) {

        const payload = {
          name: formData.name,
          password: formData.password,
          email: formData.email,
          role: "ROLE_CUSTOMER",
          phonenumber: "+4322342234",
          address: {
              street: formData.address, 
              zipcode: formData.zip,
              city: formData.city,
              country: formData.country
          }
        };
        console.log("Sending this data:", JSON.stringify(payload));

        try {   
          // Send formData to backend using fetch 
          const response = await fetch("http://localhost:8080/api/v1/signup", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
          });
          
          // Check if the response is successful
          if (response.ok) {
            console.log("Registration was successful!");
            const responseData = response.json();
            console.log("Response from backend:", responseData);
            
            const token = response.token;
            localStorage.setItem('token', token);

            // Redirect to the home page or any other route if needed
            this.$router.push({ path: '/' });
          } else {
            if(Object.keys(response).length === 0) {
              //Response is leer -> wsh 403 Forbidden
              this.errorMessage = "An error occurred. Please try again later. ("+response.status+" "+response.statusText+")";
            } else {
              const errorData = response.json();
              this.errorMessage = "Error registering user: "+errorData.message;
            }
            this.isErrorModalVisible = true;
          }
        } catch (error) {
            console.error("Network error: "+ error);
        }
      }
    },
  }
  </script>
  