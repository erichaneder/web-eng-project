<template>
    <div class="mb-4">
      <label :for="id" class="block text-sm mb-1">{{ label }}:</label>
      <input :type="type" :id="id" :placeholder="placeholder" class="w-full h-10 px-2 border rounded" required>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      id: String,
      label: String,
      type: String,
      placeholder: String
    }
  }
  </script>
  
  
  
  
  