<template>
  <div class="mb-4">
    <label :for="id" class="block text-sm mb-1">{{ label }}:</label>
    <!-- Render a select dropdown for 'select' type -->
    <select v-if="type === 'select'" :id="id" @blur="handleBlur" :value="modelValue" @change="$emit('update:modelValue', $event.target.value)" class="w-full h-10 px-2 border rounded">
      <option v-for="option in options" :key="option.value" :value="option.value">{{ option.label }}</option>
    </select>

    <!-- Render an input field for other types -->
    <input v-else :type="type" :id="id" @blur="handleBlur" @focus="showToolTip = true" :value="modelValue" @input="$emit('update:modelValue', $event.target.value)" :placeholder="placeholder" class="w-full h-10 px-2 border rounded" />
    <!-- Tooltip for password-->
    <div v-if="type === 'password' && showToolTip" class="mt-1 bg-teal-700 text-white text-xs p-2 rounded shadow-lg">
        Password must have at least 12 characters, 1 upper/lower case letter, 1 number and 1 symbol
      </div>
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
    error: String,
    options: Array
  },
  data() {
    return {
      showToolTip: false
    };
  },
  methods: {
    handleBlur(event) {
      this.showToolTip = false;
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
