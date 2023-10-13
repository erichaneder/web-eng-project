<template>
    <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
        <NormalHeading text="Your Profile" />
        
        <!-- Logged In View -->
        <div v-if="user.isLoggedIn">
          <h2 class="text-xl mb-4">Welcome, {{ user.name }}</h2>
          <p class="text-gray-600 mb-4">Email: {{ user.email }}</p>
          <!-- Additional User Info Here -->
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
import NormalHeading from '@/components/atoms/NormalHeading.vue';

  export default {
    data() {
        return {
            user: {
                isLoggedIn: false,
                name: 'John Doe',
                email: 'john@example.com'
            }
        };
    },
    components: { NormalHeading }
}
  </script>