<template>
  <div v-if="isVisible" class="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
    <div class="bg-white p-4 rounded shadow-md w-96">
      <p class="text-red-600 font-bold">{{ errorMessage }}</p>
      <CustomButton @clicked="closeModal" customButtonStyle="mt-4 p-2 bg-red-600 text-white rounded">Close</CustomButton>
    </div>
  </div>
</template>
  
<script>
import CustomButton from '@/components/atoms/Button.vue'

export default {
  props: {
    isVisible: Boolean,
    errorMessage: String
  },
  components: {
    CustomButton
  },
  methods: {
    closeModal() {
      this.$emit('update:isVisible', false);
    }
  }
}
</script>
  