<template>
  <div class="mb-4">
    <label :for="id" class="block text-sm mb-1">{{ label }}:</label>
    <input
      :type="type"
      :id="id"
      :value="modelValue"
      @blur="handleBlur"
      :placeholder="placeholder"
      class="w-full h-10 px-2 border rounded"
    />
    <div class="text-red-500" v-if="error">{{ error }}</div>
  </div>
</template>

<script>
export default {
  props: {
    id: String,
    modelValue: String,
    label: String,
    type: String,
    placeholder: String,
    validateField: Function,
    error: String
  },
  methods: {
    handleBlur(event) {
      const newValue = event.target.value;
      // Emit the updated value to the parent component
      this.$emit('update:modelValue', newValue);

      // Call validation function if provided
      if (this.validateField) {
        this.validateField(newValue);
      }
    }
  }
};
</script>
