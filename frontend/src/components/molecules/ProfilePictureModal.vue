<!-- ProfilePictureModal.vue -->
<template>
    <div v-if="isVisible" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div class="bg-white p-4 rounded shadow-md w-96">
        <SubHeading text="Update Profile Picture" />
        <input type="file" @change="onFileSelected" />
        <CustomButton @clicked="updateProfilePicture" customButtonStyle="mt-4 mr-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Update</CustomButton>
        <CustomButton @clicked="cancel" customButtonStyle="mt-4 bg-gray-300 text-black p-2 rounded hover:bg-gray-400">Cancel</CustomButton>
        </div>
    </div>
  </template>
  
  <script>
  import CustomButton from '@/components/atoms/Button.vue';
  import SubHeading from "@/components/atoms/SubHeading.vue";

  export default {
    props: ['isVisible'],
    data() {
      return {
        selectedFile: null,
      };
    },
    components: {
        CustomButton,
        SubHeading
    },
    methods: {
        onFileSelected(event) {
            const file = event.target.files[0];
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = e => {
                this.selectedFile = e.target.result; //base64 string
            };
        },
        updateProfilePicture() {
            if (this.selectedFile) {
                console.log("New pic: " + this.selectedFile);
                this.$emit('updateProfilePicture', this.selectedFile);
            }
            this.closeModal();
        },
        cancel() {
            this.closeModal();
        },
        closeModal() {
            this.selectedFile = null;
            this.$emit('update:isVisible', false);
        }
    }
  };
  </script>
  