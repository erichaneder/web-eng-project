<template>
    <div class="min-h-screen flex items-center justify-center bg-gray-100">
      <div class="w-96 bg-white p-8 rounded shadow mt-[-150px]">
        <h1 class="text-pink-800 font-bold font-sans text-4xl text-center">Login</h1>
        <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
        <form>
          <div class="mb-4">
            <label for="email" class="block text-sm mb-1">Email:</label>
            <input type="email" id="email" class="w-full h-10 px-2 border rounded" required>
          </div>
          <div class="mb-6">
            <label for="password" class="block text-sm mb-1">Password:</label>
            <input type="password" id="password" class="w-full h-10 px-2 border rounded" required>
          </div>
          <button type="submit" class="w-full bg-pink-800 text-white p-2 rounded hover:bg-red-400">Login</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'LoginComponent',
  }
  </script>
  