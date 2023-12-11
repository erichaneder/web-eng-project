<template>
  <div class="min-h-screen flex flex-col items-center">
    <div class="w-96 bg-white p-8 rounded shadow mt-20">
      <NormalHeading text="Register" />
      <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
      <FormComponent
        :form-fields="formFields"
        :form-schema="registerValidationSchema"
        :initial-form-data="initialRegisterFormData"
        @formSubmit="submit"
      >
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
import { object, string, ref } from "yup";

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
      store: useCompleteStore(),
      formFields: [
        { id: "name", type: "text", label: "Name", placeholder: "Your Name" },
        { id: "address", type: "text", label: "Address", placeholder: "Your Address" },
        { id: "city", type: "text", label: "City", placeholder: "Your City" },
        { id: "zip", type: "text", label: "Zip", placeholder: "Your Zip" },
        { id: "country", type: "select", label: "Country", placeholder: "Your Country",
          options: [
            { label: "Austria (AT)", value: "AT" },
            { label: "Germany (DE)", value: "DE" },
            { label: "Switzerland (CH)", value: "CH" },
            { label: "United States (US)", value: "US" },
            { label: "Canada (CA)", value: "CA" },
            { label: "United Kingdom (UK)", value: "UK" },
            { label: "France (FR)", value: "FR" },
            { label: "Spain (ES)", value: "ES" },
            { label: "Japan (JP)", value: "JP" },
          ]
        },
        { id: "email", type: "text", label: "Email", placeholder: "Your Email" },
        { id: "password", type: "password", label: "Password", placeholder: "Your Password" },
        { id: "password2", type: "password", label: "Repeat Passowrd", placeholder: "Repeat Password" }
      ],
      registerValidationSchema: object().shape({
        name: string().required('Name is required'),
        salutation: string().required('Salutation is required'),
        otherInfo: string().when('salutation', {
          is: (val) => val === "Other",
          then: (schema) => schema.required('Please specify your salutation')
        }),
        address: string().required('Address is required'),
        city: string().required('City is required'),
        zip: string().required('ZIP code is required'),
        country: string().required('Country is required'),
        email: string().email('Invalid email format').required('Email is required'),
        password: string().required('Password is required!').min(12, 'Password must be min. 12 characters long').matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/, 'Password must include uppercase, lowercase, numbers, and symbols'),
        password2: string().oneOf([ref('password'), null], 'Passwords must match')
      }),
      initialRegisterFormData: {
        salutation: '',
        otherInfo: '',
        name: '',
        address: '',
        city: '',
        zip: '',
        country: '',
        email: '',
        password: '',
        password2: ''
      }
    }
  },
  methods: {
    async submit(formData) {

      const payload = {
        salutation: formData.salutation==="other"?formData.otherInfo:formData.salutation,
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
          this.store.login({username: formData.email, role: responseData.role, userId: responseData.userid});
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
