<template>
    <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
        <NormalHeading text="Edit User" />
         <div v-if="user">
          <h2 class="text-xl mb-4">Profile Data of: {{ user.name }}</h2>
          
          <div v-if="!isEditing">
            <p class="text-gray-600 mb-4">Salutation: {{ user.salutation }}</p>
            <p class="text-gray-600 mb-4">Email: {{ user.email }}</p>
            <p class="text-gray-600 mb-4">Street: {{ user.street }}</p>
            <p class="text-gray-600 mb-4">Zip: {{ user.zipcode }}</p>
            <p class="text-gray-600 mb-4">City: {{ user.city }}</p>
            <p class="text-gray-600 mb-4">Country: {{ user.country }}</p>
            <CustomButton @click="isEditing = true" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Edit Profile</CustomButton>
          </div>
          
          <!-- Editing Profile View -->
          <div v-else>
            <div class="mb-4">
              <label for="name" class="block text-sm mb-1 text-gray-600">Name:</label>
              <input v-model="user.name" type="text" id="name" class="w-full h-10 px-2 border rounded">
            </div>
            <div class="mb-4">
              <label for="salutation" class="block text-sm mb-1 text-gray-600">Salutation:</label>
              <input v-model="user.salutation" type="text" id="salutation" class="w-full h-10 px-2 border rounded">
            </div>
            <div class="mb-4">
              <label for="email" class="block text-sm mb-1 text-gray-600">Email:</label>
              <input v-model="user.email" type="email" id="email" class="w-full h-10 px-2 border rounded">
            </div>
            <div class="mb-4">
              <label for="street" class="block text-sm mb-1 text-gray-600">Street:</label>
              <input v-model="user.street" type="text" id="street" class="w-full h-10 px-2 border rounded">
            </div>
            <div class="mb-4">
              <label for="zip" class="block text-sm mb-1 text-gray-600">Zip:</label>
              <input v-model="user.zipcode" type="text" id="zip" class="w-full h-10 px-2 border rounded">
            </div>
            <div class="mb-4">
              <label for="city" class="block text-sm mb-1 text-gray-600">City:</label>
              <input v-model="user.city" type="text" id="city" class="w-full h-10 px-2 border rounded">
            </div>
            <div class="mb-4">
              <label for="country" class="block text-sm mb-1 text-gray-600">Country:</label>
              <select v-model="user.country" id="country" class="w-full h-10 px-2 border rounded">
                <option disabled value="">Please select one</option>
                <option>Germany</option>
                <option>Austria</option>
                <option>Switzerland</option>
                <optgroup label="Other Countries">
                  <option>United States</option>
                  <option>Canada</option>
                  <option>United Kingdom</option>
                  <option>France</option>
                  <option>Italy</option>
                  <option>Spain</option>
                  <option>Japan</option>
                  <option>Australia</option>
                  <option>India</option>
                  <option>Brazil</option>
                </optgroup>
              </select>
            </div>
            <CustomButton @clicked="updateProfile()" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Save Changes</CustomButton>
            <CustomButton @clicked="cancel()" customButtonStyle="mt-4 bg-gray-300 text-black p-2 rounded hover:bg-gray-400 ml-4">Cancel</CustomButton>
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
  import { useCompleteStore } from "@/store/store";
  import axios from 'axios';
  
  export default {
    data() {
        return {
          user: null,
          isEditing: false,
          isErrorModalVisible: false,
          errorMessage: "",
          store: useCompleteStore()
        };
    },
    components: { 
      NormalHeading,
      CustomButton, 
      ErrorModal
    },
    methods: {
      async updateProfile() {
        const userId = parseInt(this.$route.params.userId);
        const payload = {
            name: this.user.name,
            email: this.user.email,
            salutation: this.user.salutation,
            address: {
                street: this.user.street,
                zipcode: this.user.zipcode,
                city: this.user.city,
                country: this.user.country
            }
        };
        await this.updateUserData(userId, payload);
        this.isEditing = false;
        await this.getUserData(userId);
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
      }
    },
    async mounted() {
        await this.getUserData(parseInt(this.$route.params.userId));
    }
  }
  </script>
  