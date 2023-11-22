<template>
  <form @submit.prevent="submit">
    <!-- Salutation Select Field -->
    <div class="mb-4">
      <label for="salutation" class="block text-sm mb-1">Salutation:</label>
      <select id="salutation" v-model="formData.salutation" class="w-full h-10 px-2 border rounded">
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
      </select>
    </div>

    <!-- Detailed Information Field (Shown if Salutation is 'other') -->
    <div v-if="formData.salutation === 'other'" class="mb-4">
      <label for="otherInfo" class="block text-sm mb-1">Other Information:</label>
      <input type="text" id="otherInfo" v-model="formData.otherInfo" maxlength="30" class="w-full h-10 px-2 border rounded">
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
  password: string().required('Password is required'),
  password2: string().required('Please repeat your password')
});

export default {
components: {
  InputField
},
data() {
  return {
    formData: {
      salutation: 'male',
      otherInfo: '',
      name: '',
      address: '',
      city: '',
      zip: '',
      country: '',
      email: '',
      password: '',
      password2: ''
    },
    fieldErrors: {
      name: null,
      address: null,
      city: null,
      zip: null,
      country: null,
      email: null,
      password: null,
      password2: null
    },
    formFields: [
        { id: "name", type: "text", label: "Name", placeholder: "Your Name" },
        { id: "address", type: "text", label: "Address", placeholder: "Your Address" },
        { id: "city", type: "text", label: "City", placeholder: "Your City" },
        { id: "zip", type: "text", label: "Zip", placeholder: "Your Zip" },
        { id: "country", type: "text", label: "Country", placeholder: "Your Country" },
        { id: "email", type: "text", label: "Email", placeholder: "Your Email" },
        { id: "password", type: "text", label: "Passowrd", placeholder: "Your Password" },
        { id: "password2", type: "text", label: "Repeat Passowrd", placeholder: "Repeat Password" }
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
