<template>
  <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <NormalHeading text="Your Profile" />
      
      <!-- Logged In View -->
      <div v-if="user.isLoggedIn">
        <h2 class="text-xl mb-4">Welcome, {{ user.name }}</h2>
        
        <div v-if="!isEditing">
          <p class="text-gray-600 mb-4">Email: {{ user.email }}</p>
          <p class="text-gray-600 mb-4">Adresse: {{ user.address }}</p>
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
            <label for="address" class="block text-sm mb-1 text-gray-600">Address:</label>
            <input v-model="user.address" type="text" id="address" class="w-full h-10 px-2 border rounded">
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
</template>

<script>
import CustomButton from '@/components/atoms/Button.vue';
import NormalHeading from '@/components/atoms/NormalHeading.vue';

export default {
  data() {
      return {
          user: {
              isLoggedIn: true,
              name: 'John Doe',
              email: 'john@example.com',
              address: 'Bahnstraße 10, 1030 Wien',
              phone: '+43892347324'
          },
          isEditing: false
      };
  },
  components: { 
    NormalHeading,
    CustomButton
  },
  methods: {
      updateProfile() {
          // logic to update user data goes here.
          // make an API call to update the user data on the server/db.
          console.log('Updated user:', this.user);
          this.isEditing = false;
      }
  }
}
</script>
