<template>
  <div class="min-h-screen bg-gray-100 py-10 px-5 flex flex-col items-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mb-10">
      <NormalHeading text="Your Profile" />
      
      <!-- Logged In View -->
      <div v-if="this.store.isLoggedIn && user">
        <h2 class="text-xl mb-4">Welcome, {{ user.name }}</h2>
        
        <div v-if="!isEditing">
          <p class="text-gray-600 mb-4">Salutation: {{ user.salutation }}</p>
          <p class="text-gray-600 mb-4">Email: {{ user.email }}</p>
          <p class="text-gray-600 mb-4">Street: {{ user.address.street }}</p>
          <p class="text-gray-600 mb-4">Zip: {{ user.address.zipcode }}</p>
          <p class="text-gray-600 mb-4">City: {{ user.address.city }}</p>
          <p class="text-gray-600 mb-4">Country: {{ user.address.country }}</p>
          <CustomButton @clicked="enableEditing()" customButtonStyle="mt-4 mr-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Edit Profile</CustomButton>
          <CustomButton @clicked="showChangePasswordModal" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Change Password</CustomButton>
        </div>
        
        <!-- Editing Profile View -->
        <div v-else>
          <FormComponent
            :initial-form-data="editableUserData"
            :form-fields="profileFormFields"
            :form-schema="profileValidationSchema"
            :special-salutation="false"
            @formSubmit="updateProfile"
          >
            <CustomButton type="submit" customButtonStyle="mt-4 bg-teal-700 text-white p-2 rounded hover:bg-teal-500">Save Changes</CustomButton>
          </FormComponent>
          <CustomButton @clicked="cancel()" customButtonStyle="mt-4 bg-gray-300 text-black p-2 rounded hover:bg-gray-400">Cancel</CustomButton>
        </div>
      </div>

      <!-- Logged Out View -->
      <div v-else>
        <p class="text-gray-600 mb-4">
          Please <router-link to="/login" class="text-teal-700">log in</router-link> or 
          <router-link to="/register" class="text-teal-700">register</router-link> to view your profile.
        </p>
      </div>
    </div>
  </div>

  <ChangePasswordModal 
    :isVisible="isChangePasswordModalVisible" 
    @update:isVisible="isChangePasswordModalVisible = $event" 
    @changePassword="onPasswordChanged" />
  <ErrorModal
    :isVisible="isErrorModalVisible"
    :errorMessage="errorMessage"
    @update:isVisible="isErrorModalVisible = $event"
  />
</template>

<script>
import CustomButton from '@/components/atoms/Button.vue';
import NormalHeading from '@/components/atoms/NormalHeading.vue';
import ErrorModal from "@/components/atoms/ErrorModal.vue";
import ChangePasswordModal from "@/components/atoms/ChangePasswordModal.vue";
import FormComponent from "@/components/molecules/FormComponent.vue";
import { useCompleteStore } from "@/store/store";
import { object, string } from "yup";

export default {
  data() {
    return {
      user: null,
      isEditing: false,
      isErrorModalVisible: false,
      isChangePasswordModalVisible: false,
      errorMessage: "",
      store: useCompleteStore(),
      profileFormFields: [
        { id: "name", type: "text", label: "Name", placeholder: "Your Name" },
        { id: "salutation", type: "text", label: "Salutation", placeholder: "Your Salutation" },
        { id: "address", type: "text", label: "Address", placeholder: "Your Address" },
        { id: "city", type: "text", label: "City", placeholder: "Your City" },
        { id: "zip", type: "text", label: "Zip", placeholder: "Your Zip" },
        { id: "country", type: "select", label: "Country", placeholder: "Your Country",
          options: [
            { label: "Austria (AT)", value: "AT" },
            { label: "Germany (DE)", value: "DE" },
            { label: "Switzerland (CH)", value: "CH" },
            { label: "United States (US)", value: "US" },
            { label: "Canada (CA)", value: "CA" },
            { label: "United Kingdom (UK)", value: "UK" },
            { label: "France (FR)", value: "FR" },
            { label: "Spain (ES)", value: "ES" },
            { label: "Japan (JP)", value: "JP" },
          ]
        },
        { id: "email", type: "text", label: "Email", placeholder: "Your Email" }
      ],
      profileValidationSchema: object().shape({
        name: string().required('Name is required'),
        salutation: string().required('Salutation is required'),
        address: string().required('Address is required'),
        city: string().required('City is required'),
        zip: string().required('ZIP code is required'),
        country: string().required('Country is required'),
        email: string().email('Invalid email format').required('Email is required')
      }),
      editableUserData: {}
    };
  },
  components: { 
    NormalHeading,
    CustomButton, 
    ErrorModal,
    FormComponent,
    ChangePasswordModal
  },
  methods: {
    async updateProfile(formData) {
        const userId = this.store.getUserId;
        const payload = {
            name: formData.name,
            email: formData.email,
            salutation: formData.salutation,
            address: {
                street: formData.address,
                zipcode: formData.zip,
                city: formData.city,
                country: formData.country
            }
        };
        await this.store.updateProfileData(userId, payload);
        let error = this.store.checkforProfileError();
        if(error) {
          this.errorMessage = error;
          this.isErrorModalVisible = true;
        }
        this.isEditing = false;

        //if user changed his name or email -> log them out because JWT token is no longer valid
        if(formData.name !== this.user.name || formData.email !== this.user.email) {
          this.logout();
        } else {
          this.user = this.store.getProfileData;
        }
    },
    enableEditing() {
      this.isEditing = true;
      this.editableUserData = {
        name: this.user.name,
        salutation: this.user.salutation,
        email: this.user.email,
        address: this.user.address.street,
        city: this.user.address.city,
        zip: this.user.address.zipcode,
        country: this.user.address.country
      };
    },
    cancel() {
      this.isEditing = false;
      this.user = this.store.getProfileData;
    },
    showChangePasswordModal() {
      this.isChangePasswordModalVisible = true;
    },
    async onPasswordChanged(newPassword) {
      const userId = this.store.getUserId;
      const payload = {
            name: this.user.name,
            email: this.user.email,
            salutation: this.user.salutation,
            address: {
                street: this.user.address.street,
                zipcode: this.user.address.zip,
                city: this.user.address.city,
                country: this.user.address.country
            },
            password: newPassword
        };
        await this.store.updateProfileData(userId, payload);
        let error = this.store.checkforProfileError();
        if(error) {
          this.errorMessage = error;
          this.isErrorModalVisible = true;
        }
      console.log('Password changed successfully');
    },
    logout() {
      localStorage.removeItem("token");
      this.store.logout();
      this.$router.push("/");
    },
  },
  async mounted() {
    if(this.store.isLoggedIn) {
      await this.store.fetchProfileData();
      this.user = this.store.getProfileData;
    }
  }
}
</script>
