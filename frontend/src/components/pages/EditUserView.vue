<template>
  <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <NormalHeading text="Edit User" />
      <div v-if="user">
        <h2 class="text-xl mb-4">Profile Data of: {{ user.name }}</h2>
        
        <div v-if="!isEditing">
          <p class="text-gray-600 mb-4">Salutation: {{ user.salutation }}</p>
          <p class="text-gray-600 mb-4">Email: {{ user.email }}</p>
          <p class="text-gray-600 mb-4">Street: {{ user.address.street }}</p>
          <p class="text-gray-600 mb-4">Zip: {{ user.address.zipcode }}</p>
          <p class="text-gray-600 mb-4">City: {{ user.address.city }}</p>
          <p class="text-gray-600 mb-4">Country: {{ user.address.country }}</p>
          <CustomButton @clicked="enableEditing()" customButtonStyle="mt-4 mr-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Edit Profile</CustomButton>
        </div>
        
        <!-- Editing Profile View -->
        <div v-else>
          <FormComponent
            :initial-form-data="editableUserData"
            :form-fields="profileFormFields"
            :form-schema="profileValidationSchema"
            :special-salutation="false"
            @formSubmit="updateProfile"
          >
            <CustomButton type="submit" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Save Changes</CustomButton>
          </FormComponent>
          <CustomButton @clicked="cancel()" customButtonStyle="mt-4 bg-gray-300 text-black p-2 rounded hover:bg-gray-400">Cancel</CustomButton>
        </div>
      </div>
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
import NormalHeading from '@/components/atoms/NormalHeading.vue';
import ErrorModal from "@/components/atoms/ErrorModal.vue";
import FormComponent from "@/components/molecules/FormComponent.vue";
import { useCompleteStore } from "@/store/store";
import { object, string } from "yup";
import axios from 'axios';

export default {
  data() {
    return {
      user: null,
      isEditing: false,
      isErrorModalVisible: false,
      errorMessage: "",
      store: useCompleteStore(),
      profileFormFields: [
        { id: "name", type: "text", label: "Name", placeholder: "Your Name" },
        { id: "salutation", type: "text", label: "Salutation", placeholder: "Your Salutation" },
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
        { id: "email", type: "text", label: "Email", placeholder: "Your Email" }
      ],
      profileValidationSchema: object().shape({
        name: string().required('Name is required'),
        salutation: string().required('Salutation is required'),
        address: string().required('Address is required'),
        city: string().required('City is required'),
        zip: string().required('ZIP code is required'),
        country: string().required('Country is required'),
        email: string().email('Invalid email format').required('Email is required')
      }),
      editableUserData: {}
    };
  },
  components: { 
    NormalHeading,
    CustomButton, 
    ErrorModal,
    FormComponent
  },
  methods: {
    async updateProfile(formData) {
        const userId = parseInt(this.$route.params.userId);
        const payload = {
            name: formData.name,
            email: formData.email,
            salutation: formData.salutation,
            address: {
                street: formData.address,
                zipcode: formData.zip,
                city: formData.city,
                country: formData.country
            }
        };
        await this.updateUserData(userId, payload);
        this.isEditing = false;
        await this.getUserData(userId);
    },
    async updateUserData(userId, payload) {
        const token = localStorage.getItem('token');
        const config = {
            headers: { Authorization: `Bearer ${token}` }
        };
        try {
          const response = await axios.put('http://localhost:8080/api/v1/user/update/' + userId, payload, config);
          if (response.status === 200) {
              const data = await response.data;
              console.log('User updated successfully:', data);
          } else {
              console.error('Error updating user:', response.status, response.statusText);
              this.errorMessage = "Error updating user: " + response.status + " " +response.statusText;
              this.isErrorModalVisible = true;
          }
        } catch (error) {
            console.error('Network error:', error);
            this.errorMessage = error.message;
            this.isErrorModalVisible = true;
        }
      },
      async cancel() {
        this.isEditing = false;
        await this.getUserData(parseInt(this.$route.params.userId));
      },
      async getUserData(userId) {
          try {
            const token = localStorage.getItem('token');
            const config = {
              headers: { Authorization: `Bearer ${token}` }
            };
            const response = await axios.get('http://localhost:8080/api/v1/user/' + userId, config);
            const userData = response.data;
            this.user = {
              ...userData, //-> ... = Spread Operator so kann man das object aufspreaden in individuelle Elemente, hier sagt man halt nimm alle Properties von product, und nacher wird das image dann umgsetzt und es werden noch extras hinzugefügt
              phone: userData.phonenumber,
              zipcode: userData.address.zipcode,
              street: userData.address.street,
              city: userData.address.city,
              country: userData.address.country,
            }; 
          } catch (error) {
            console.error('Error fetching user:', error);
          }
        },
        enableEditing() {
        this.isEditing = true;
        this.editableUserData = {
          name: this.user.name,
          salutation: this.user.salutation,
          email: this.user.email,
          address: this.user.address.street,
          city: this.user.address.city,
          zip: this.user.address.zipcode,
          country: this.user.address.country
        };
      },
    },
    async mounted() {
      await this.getUserData(parseInt(this.$route.params.userId));
    }
  }
</script>
