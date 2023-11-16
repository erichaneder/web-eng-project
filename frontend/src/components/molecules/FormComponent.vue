<template>
  <form @submit.prevent="submit">
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
    />
    <slot></slot> <!-- slot for submit button -->
  </form>
</template>

<!--Falls mal nebeneinander zum displayn is : <div class="flex space-x-4"> -->

<script>
import InputField from "@/components/atoms/InputField.vue";
import { object, string } from "yup";

const formSchema = object().shape({
  name: string().required('Name is required'),
  address: string().required('Address is required'),
  city: string().required('City is required'),
  zip: string().required('ZIP code is required'),
  country: string().required('Country is required'),
  email: string().email('Invalid email format').required('Email is required'),
  password: string().required('Password is required')
});

export default {
components: {
  InputField
},
data() {
  return {
    formData: {
      name: '',
      address: '',
      city: '',
      zip: '',
      country: '',
      email: '',
      password: ''
    },
    fieldErrors: {
      name: null,
      address: null,
      city: null,
      zip: null,
      country: null,
      email: null,
      password: null
    },
    formFields: [
        { id: "name", type: "text", label: "Name", placeholder: "Your Name" },
        { id: "address", type: "text", label: "Address", placeholder: "Your Address" },
        { id: "city", type: "text", label: "City", placeholder: "Your City" },
        { id: "zip", type: "text", label: "Zip", placeholder: "Your Zip" },
        { id: "country", type: "text", label: "Country", placeholder: "Your Country" },
        { id: "email", type: "text", label: "Email", placeholder: "Your Email" },
        { id: "password", type: "text", label: "Passowrd", placeholder: "Your Password" }
      ]
  };
},
methods: {
  validateField(value, fieldName) {
    console.log("Field: " +fieldName+", Value: "+value);
    formSchema.validateAt(fieldName, { [fieldName]: value })
        .then(() => {
          this.fieldErrors[fieldName] = null;
        })
        .catch(error => {
          this.fieldErrors[fieldName] = error.message;
        });
  },
  submit() {
    formSchema.validate(this.formData, { abortEarly: false })
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
