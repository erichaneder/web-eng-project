<template>
  <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <h1 class="text-2xl font-bold mb-5 text-pink-800">About Us</h1>
      <p class="text-gray-600 mb-4">
        Welcome to Sneaker Shop, your number one source for all things sneakers. 
        We're dedicated to giving you the very best of sneakers, with a focus on dependability, customer service and uniqueness.
      </p>
      <p class="text-gray-600 mb-4">
        Founded in 2023 by a random dude, Sneaker Shop has come a long way 
        from its beginnings in a your moms bedroom. 
        When a random dude first started out, his passion for "seeing the world burn"
        drove him to create this online shop, 
        and gave him the impetus to turn hard work and inspiration into to a booming online store.
      </p>
      <p class="text-gray-600 mb-4">
        We hope you enjoy our products as much as we enjoy offering them to you. 
        If you have any questions or comments, please don't hesitate to <a href="/contact" class="text-pink-800 underline">contact us</a>.
      </p>
    </div>
    <div class="flex flex-col items-center w-full max-w-2xl">
      <h2 class="text-xl font-bold mb-4 text-pink-800">Our Team</h2>
      <div class="bg-white p-8 rounded-lg shadow-md w-full mb-8">
        <h3 class="font-semibold text-lg mb-2 text-gray-700">[Team Member Name]</h3>
        <p class="text-gray-600">
          [Brief description about the team member, their role, and a bit of fun personal detail. E.g., "John is our marketing lead. When he's not creating campaigns, he loves hiking with his dog."]
        </p>
      </div>
      <!-- Repeat the above div for each team member -->
    </div>
  </div>
</template>

  <script>
  export default {
    name: 'AboutComponent',
  }
  </script>
  