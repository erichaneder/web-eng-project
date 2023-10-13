<template>
    <SubHeading text="What Our Customers Say" />
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4 w-full max-w-3xl">
        <div v-for="testimonial in testimonials" :key="testimonial.name" class="bg-white p-5 rounded-lg shadow-md">
          <p class="text-gray-600 italic mb-4">"{{ testimonial.quote }}"</p>
          <div class="flex items-center">
            <img :src="testimonial.image" alt="" class="w-10 h-10 rounded-full mr-4">
            <p class="text-gray-700">- {{ testimonial.name }}</p>
          </div>
        </div>
      </div>
  </template>
  
  <script>
import SubHeading from '../atoms/SubHeading.vue';

  export default {
    name: "CustomerTestimonials",
    data() {
        return {
            testimonials: [
                { name: 'Alex Johnson', image: require('@/assets/alex.jpg'), quote: 'Amazing quality and super comfortable. Sneaker Street is now my go-to place for all my sneaker needs!' },
                { name: 'Lisa Cumber', image: require('@/assets/lisa.jpg'), quote: 'I really like how the shoes feel on my feet. Like walking on clouds!' },
                { name: 'Ken Harman', image: require('@/assets/ken.jpg'), quote: 'The shoes look fresh af...' },
                // ... Other Testimonials ...
            ]
        };
    },
    components: { SubHeading }
}
  </script>
  