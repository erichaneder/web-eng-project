<template>
  <div class="min-h-screen flex flex-col items-center">
    <div class="w-96 bg-white p-8 rounded shadow mt-20">
      <NormalHeading text="Register" />
      <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
      <FormComponent @formSubmit="submit">
        <CustomButton type="sumbit" customButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Register</CustomButton>
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
import CustomButton from '@/components/atoms/Button.vue';
import FormComponent from "@/components/molecules/FormComponent.vue";
import NormalHeading from "@/components/atoms/NormalHeading.vue";
import ErrorModal from '@/components/atoms/ErrorModal.vue'
import { useCompleteStore } from '@/store/store';

export default {
  components: {
    FormComponent,
    NormalHeading,
    ErrorModal,
    CustomButton
  },
  data() {
    return {
      isErrorModalVisible: false,
      errorMessage: '',
      store: useCompleteStore()
    }
  },
  methods: {
    async submit(formData) {

      const payload = {
        salutation: formData.salutation?formData.salutation:formData.otherInfo,
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
        const response = await fetch("http://localhost:8080/api/v1/user/signup", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        });
        
        // Check if the response is successful
        if (response.ok) {
          console.log("Registration was successful!");
          const responseData = await response.json();
          
          localStorage.setItem("token", responseData.token);
          this.store.login({email: formData.email, role: responseData.role, userId: responseData.userid});
          // Redirect to the home page
          this.$router.push({ path: '/' });
        } else {
          const errorData = await response.json();
          this.errorMessage = "Error registering user: "+errorData.message;
          this.isErrorModalVisible = true;
        }
      } catch (error) {
          console.error("Network error: "+ error);
      }
    }
  },
}
</script>
