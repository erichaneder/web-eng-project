<template>
  <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <NormalHeading text="Your Profile" />
      
      <!-- Logged In View -->
      <div v-if="isLoggedIn">
        <h2 class="text-xl mb-4">Welcome, {{ user.name }}</h2>
        
        <div v-if="!isEditing">
          <p class="text-gray-600 mb-4">Email: {{ user.email }}</p>
          <p class="text-gray-600 mb-4">Street: {{ user.street }}</p>
          <p class="text-gray-600 mb-4">Zip: {{ user.zipcode }}</p>
          <p class="text-gray-600 mb-4">City: {{ user.city }}</p>
          <p class="text-gray-600 mb-4">Country: {{ user.country }}</p>
          <p class="text-gray-600 mb-4">Phone: {{ user.phone }}</p>
          <!-- Additional User Info Here -->
          <CustomButton @click="isEditing = true" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Edit Profile</CustomButton>
        </div>
        
        <!-- Editing Profile View -->
        <div v-else>
          <div class="mb-4">
            <label for="name" class="block text-sm mb-1 text-gray-600">Name:</label>
            <input v-model="user.name" type="text" id="name" class="w-full h-10 px-2 border rounded">
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
            <input v-model="user.country" type="text" id="country" class="w-full h-10 px-2 border rounded">
          </div>
          <div class="mb-4">
            <label for="phone" class="block text-sm mb-1 text-gray-600">Phone:</label>
            <input v-model="user.phone" type="phone" id="phone" class="w-full h-10 px-2 border rounded">
          </div>
          <CustomButton @click="updateProfile" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Save Changes</CustomButton>
          <CustomButton @click="isEditing = false" customButtonStyle="mt-4 bg-gray-300 text-black p-2 rounded hover:bg-gray-400 ml-4">Cancel</CustomButton>
        </div>
      </div>

      <!-- Logged Out View -->
      <div v-else>
        <p class="text-gray-600 mb-4">
          Please <router-link to="/login" class="text-teal-700">log in</router-link> or 
          <router-link to="/register" class="text-teal-700">register</router-link> to view your profile.
        </p>
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
import axios from 'axios';

export default {
  data() {
      return {
          user: null,
          isEditing: false,
          isLoggedIn: false,
          isErrorModalVisible: false,
          errorMessage: "",
      };
  },
  components: { 
    NormalHeading,
    CustomButton, 
    ErrorModal
  },
  methods: {
    async updateProfile() {
        const userId = localStorage.getItem("userId");
        try {
            const payload = {
                name: this.user.name,
                email: this.user.email,
                phonenumber: this.user.phone,
                address: {
                    street: this.user.street,
                    zipcode: this.user.zipcode,
                    city: this.user.city,
                    country: this.user.country
                }
            };
            const response = await axios.put('http://localhost:8080/api/v1/user/update/' + userId, payload);

            // Check if the response is successful and update local user data
            if (response.status === 200) {
                console.log('User updated successfully:', response.data);
                this.user = { ...this.user, ...response.data };
            } else {
                console.error('Error updating user:', response.status, response.statusText);
                this.errorMessage = "Error updating user: " + response.status + " " +response.statusText;
                this.isErrorModalVisible = true;
            }

            this.isEditing = false;
        } catch (error) {
            console.error('Network error:', error);
            // Handle errors such as network issues, server down, etc.
        }
    },
      async getUserData() {
        var userId;
        if(this.$route.params.id) {
          userId = parseInt(this.$route.params.id);
        } else {
          userId = parseInt(localStorage.getItem("userId"));
        }
  
        try {
          const response = await axios.get('http://localhost:8080/api/v1/user/' + userId);
          const userData = response.data;
          console.log(JSON.stringify(userData, null, 2)); // Formats and prints the object);
          this.user = {
          ...userData, //-> ... = Spread Operator so kann man das object aufspreaden in individuelle Elemente, hier sagt man halt nimm alle Properties von product, und nacher wird das image dann umgsetzt und es werden noch extras hinzugefügt
          test: "123",
          phone: userData.phonenumber,
          zipcode: userData.address.zipcode,
          street: userData.address.street,
          city: userData.address.city,
          country: userData.address.country,
        };
        this.isLoggedIn = true;
        } catch (error) {
          console.error('Error fetching user:', error);
          this.user = {
            name: "John Doe",
            email: 'john@example.com',
            street: 'Bahnstraße 10',
            zipcode: '1030',
            city: 'Wien',
            country: 'Austria',
            phone: '+43892347324'
          };
          this.isLoggedIn = true;
        }
      }
  },
  mounted() {
    this.getUserData();
  }
}
</script>
