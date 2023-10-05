<template>
  <nav class="bg-pink-800 p-4">
    <div class="container mx-auto flex justify-between items-center">
      <a href="#" class="text-white text-2xl font-bold">SneakerShop</a>
      <ul class="flex space-x-4">
        <li class="inline mx-2 text-white hover:text-pink-400 cursor-pointer">
            <router-link to="/" class="text-white">Home</router-link>
        </li>
        <li class="inline mx-2 text-white hover:text-pink-400 cursor-pointer">
            <router-link to="/about" class="text-white">About</router-link>
        </li>
        <li class="inline mx-2 text-white hover:text-pink-400 cursor-pointer">
            <router-link to="/login" class="text-white">Login</router-link>
        </li>
        <li class="inline mx-2 text-white hover:text-pink-400 cursor-pointer">
            <router-link to="/register" class="text-white">Register</router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>
  
  <script>
  export default {
   name: 'NavbarComponent'
  }
  </script>
  
  <style>
  </style>