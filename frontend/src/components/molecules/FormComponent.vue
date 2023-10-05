<template>
    <form>
      <InputField id="name" label="Name" type="text" placeholder="Your Name"/>
      <InputField id="address" label="Address" type="text" placeholder="Your Address"/>
      <InputField id="email" label="Email" type="email" placeholder="Your Email"/>
      <InputField id="password" label="Password" type="password" placeholder="Your Password"/>
      <slot></slot> <!-- Slot for additional content, e.g., submit button -->
    </form>
  </template>
  
  <script>
  import InputField from "@/components/atoms/InputField.vue";
  
  export default {
    components: {
      InputField
    }
  }
  </script>
  