<template>
    <SubHeading text="What Our Customers Say" />
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4 w-full max-w-3xl">
        <div v-for="testimonial in testimonials" :key="testimonial.name" class="bg-white p-5 rounded-lg shadow-md transform transition-transform duration-300 hover:scale-105 hover:bg-gray-50 hover:shadow-lg">
          <p class="text-gray-600 italic mb-4">"{{ testimonial.quote }}"</p>
          <div class="flex items-center">
            <img v-lazy="testimonial.image" :alt="testimonial.name" class="w-10 h-10 rounded-full mr-4">
            <p class="text-gray-700">- {{ testimonial.name }}</p>
          </div>
        </div>
      </div>
  </template>
  
  <script>
import SubHeading from '@/components/atoms/SubHeading.vue';

import alex from '@/assets/alex.webp';
import lisa from '@/assets/lisa.webp';
import ken from '@/assets/ken.webp';

  export default {
    name: "CustomerTestimonials",
    data() {
        return {
            testimonials: [
                { name: 'Alex Johnson', image: alex, quote: 'Amazing quality and super comfortable. Sneaker Street is now my go-to place for all my sneaker needs!' },
                { name: 'Lisa Cumber', image: lisa, quote: 'I really like how the shoes feel on my feet. Like walking on clouds!' },
                { name: 'Ken Harman', image: ken, quote: 'The shoes look fresh af...' },
            ]
        };
    },
    components: { SubHeading }
}
  </script>
  