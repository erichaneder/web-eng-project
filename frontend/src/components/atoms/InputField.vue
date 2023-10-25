<template>
    <div class="mb-4">
      <label :for="id" class="block text-sm mb-1">{{ label }}:</label>
      <input :type="type" :id="id" @input="updateValue($event)" @blur="validateField" :value="value" :placeholder="placeholder" class="w-full h-10 px-2 border rounded" required>
      <div class="text-red-500" v-if="fieldError">{{ fieldError }}</div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';

  export default {
    props: {
      id: String,
      value: String,
      label: String,
      type: String,
      placeholder: String,
      validation: {
        type: Object,
        default: null,
      },
    },
    setup(props, context) {
        const fieldError = ref(null);

        const updateValue = (event) => {
            context.emit('input', event.target.value);
        };

        const validate = () => {
            if (props.validation) {
                props.validation.validate(props.value)
                    .then(() => {
                        fieldError.value = null; // No error
                    })
                    .catch(err => {
                        fieldError.value = err.message; // Set the error message
                        context.emit('error', fieldError.value); // This goes in the validate method of InputField
                    });
            }
        };

        return {
            fieldError,
            updateValue,
            validate
        };
    }
  }
  </script>
  
  
  
  
  