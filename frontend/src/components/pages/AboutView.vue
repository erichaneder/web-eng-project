<template>
  <div class="min-h-screen py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <NormalHeading text="About Us"/>
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
        If you have any questions or comments, please don't hesitate to <router-link to="/contact" class="text-teal-500 underline">contact us</router-link>.
      </p>
    </div>
    <div class="flex flex-col items-center w-full max-w-2xl">
      <SubHeading text="Our Team"/>
      <div class="bg-white p-8 rounded-lg shadow-md w-full mb-8">
        <h3 class="font-semibold text-lg mb-2 text-gray-700">John Johnson</h3>
        <p class="text-gray-600">
          John is our marketing lead. When he's not creating campaigns, he loves hiking with his dog. Ever since he was a little boy he dreamt of selling shoes.
        </p>
      </div>
      <div class="bg-white p-8 rounded-lg shadow-md w-full mb-8">
        <h3 class="font-semibold text-lg mb-2 text-gray-700">Anja Jackson</h3>
        <p class="text-gray-600">
          Anja is our product genius. When he's not creating new shoes, she loves swimming with her dog. Ever since she was a little girl she dreamt of selling shoes.
        </p>
      </div>
      <div class="bg-white p-8 rounded-lg shadow-md w-full mb-8">
        <h3 class="font-semibold text-lg mb-2 text-gray-700">Tom Sawyer</h3>
        <p class="text-gray-600">
          Tom is our finance guy. When he's not hiding from the tax office, he loves hiking with his dog. Ever since he was a little boy he dreamt of selling shoes.
        </p>
      </div>
      <!-- Hier noch maybe weitere team member rein geben -->
    </div>
  </div>
</template>

<script>
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import SubHeading from "@/components/atoms/SubHeading.vue";

  export default {
    components: {
      NormalHeading,
      SubHeading
    }
  }
  </script>
  