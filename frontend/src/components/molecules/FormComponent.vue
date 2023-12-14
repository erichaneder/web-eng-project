<template>
  <form @submit.prevent="submit">
    
    <div v-if="specialSalutation">
      <!-- Salutation Select Field -->
      <div class="mb-4">
        <label for="salutation" class="block text-sm mb-1">Salutation:</label>
        <select id="salutation" v-model="formData.salutation" @blur="validateField(formData.salutation, 'salutation')" class="w-full h-10 px-2 border rounded">
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select>
        <div class="text-red-500" v-if="fieldErrors.salutation">{{ fieldErrors.salutation }}</div>
      </div>

      <!-- Detailed Information Field (Shown if Salutation is 'other') -->
      <div v-if="formData.salutation === 'Other'" class="mb-4">
        <label for="otherInfo" class="block text-sm mb-1">Other Information:</label>
        <input type="text" id="otherInfo" @blur="validateField(formData.otherInfo, 'otherInfo')" v-model="formData.otherInfo" maxlength="30" class="w-full h-10 px-2 border rounded">
        <div class="text-red-500" v-if="fieldErrors.otherInfo">{{ fieldErrors.otherInfo }}</div>
      </div>
    </div>
    

    <InputField
      v-for="field in formFields"
      :key="field.id"
      :id="field.id"
      :type="field.type"
      :label="field.label"
      :placeholder="field.placeholder"
      v-model="formData[field.id]"
      :validateField="(value) => validateField(value, field.id)"
      :error="fieldErrors[field.id]"
      :options="field.options"
    />
    <slot></slot> <!-- slot for submit button -->
  </form>
</template>

<!--Falls mal nebeneinander zum displayn is : <div class="flex space-x-4"> -->

<script>
import InputField from "@/components/atoms/InputField.vue";

export default {
components: {
  InputField
},
props: {
    formFields: {
      type: Array,
      required: true
    },
    formSchema: {
      type: Object,
      required: true
    },
    initialFormData: {
      type: Object,
      required: true
    },
    specialSalutation: {
      type: Boolean,
      required: true
    }
  },
data() {
  return {
    formData: this.initialFormData,
    fieldErrors: {}
  };
},
methods: {
  validateField(value, fieldName) {
    console.log("Field: " +fieldName+", Value: "+value);
    this.formSchema.validateAt(fieldName, { ...this.formData, [fieldName]: value })
        .then(() => {
          this.fieldErrors[fieldName] = null;
          // If the password field is updated, also validate password2 hack
          if (fieldName === 'password') {
          this.validateField(this.formData.password2, 'password2');
        }
        })
        .catch(error => {
        if (fieldName === 'password2' && !this.formData.password2) { //this is needed hack
          // Clear the error for 'password2' if it's empty
          this.fieldErrors[fieldName] = null;
        } else {
          // Set the error for other cases
          this.fieldErrors[fieldName] = error.message;
        }
        });
  },
  submit() {
    console.log("FormData: " + JSON.stringify(this.formData));
    this.formSchema.validate(this.formData, { abortEarly: false })
      .then(() => {
        this.$emit('formSubmit', this.formData);
      })
      .catch(error => {
        error.inner.forEach(err => {
          console.log("Validation failed! "+ err.message);
          this.fieldErrors[err.path] = err.message;
        });
      });
  }
}
};
</script>
