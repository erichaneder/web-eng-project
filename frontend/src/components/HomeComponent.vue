<template>
    <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
      <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
        <h1 class="text-2xl font-bold mb-5 text-pink-800">Welcome to Sneaker Shop</h1>
        <p class="text-gray-600 mb-4">
          Step into the world of style and comfort. Explore our diverse collection of top-tier sneakers perfect for every occasion.
        </p>
      </div>
      
        <!-- Featured Products Section -->
        <div class="mb-10 w-full max-w-4xl flex flex-col items-center">
        <h2 class="text-xl font-bold mb-4 text-pink-800">Featured Products</h2>
        <div class="grid grid-cols-1 gap-4 w-full max-w-md">
            <!-- Single Product Card -->
            <div class="bg-white p-5 rounded-lg shadow-md">
            <img src="https://via.placeholder.com/300" alt="Epic Sneaker" class="w-full h-40 object-cover rounded mb-4"/>
            <h3 class="text-lg font-semibold text-gray-700 mb-2">Epic Sneaker</h3>
            <p class="text-gray-600 mb-2">Stylish, comfortable, and durable. The Epic Sneaker is suitable for every adventure.</p>
            <p class="text-pink-800 font-bold">$120</p>
            </div>
            <!-- More products can be added similarly -->
        </div>
        </div>
      
      <!-- Testimonials Section -->
      <div class="w-full max-w-2xl">
        <h2 class="text-xl font-bold mb-4 text-pink-800">What Our Customers Say</h2>
        <div class="bg-white p-8 rounded-lg shadow-md mb-8">
          <p class="text-gray-600 italic">"Amazing quality and super comfortable. Sneaker Street is now my go-to place for all my sneaker needs!"</p>
          <p class="text-gray-700 mt-4">- Alex Johnson</p>
        </div>
        <!-- Add more testimonials as desired -->
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "HomeComponent"
  };
  </script>
  