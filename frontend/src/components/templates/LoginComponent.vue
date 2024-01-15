<template>
  <div class="min-h-screen flex flex-col items-center px-5">
    <div class="w-96 bg-white p-8 rounded shadow mt-20">
      <NormalHeading text="Login" />
      <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
      <form @submit.prevent="submit">
        <div class="mb-4">
          <label for="username" class="block text-sm mb-1">Username / Email:</label>
          <input
            type="text"
            id="username"
            class="w-full h-10 px-2 border rounded"
            v-model="form.values.username"
            @blur="validate('username')"
          />
          <p class="text-red-500" v-if="!!form.errors.username">
            {{ form.errors.username }}
          </p>
        </div>
        <div class="mb-6">
          <label for="password" class="block text-sm mb-1">Password:</label>
          <input
            type="password"
            id="password"
            class="w-full h-10 px-2 border rounded"
            v-model="form.values.password"
            @blur="validate('password')"
          />
          <p class="text-red-500" v-if="!!form.errors.password">
            {{ form.errors.password }}
          </p>
        </div>
        <CustomButton
          type="submit"
          CustomButtonStyle="w-full bg-teal-700 text-white p-2 rounded hover:bg-teal-500"
          >Login</CustomButton
        >
      </form>
    </div>
  </div>

  <ErrorModal
    :isVisible="isErrorModalVisible"
    :errorMessage="errorMessage"
    @update:isVisible="isErrorModalVisible = $event"
  />
</template>

<script>
import CustomButton from "@/components/atoms/Button.vue";
import NormalHeading from "@/components/atoms/NormalHeading.vue";
import axios from 'axios';
import ErrorModal from "@/components/atoms/ErrorModal.vue";
import { object, string } from "yup";
import { useCompleteStore } from "@/store/store";
import { jwtDecode } from "jwt-decode";

const loginSchema = object().shape({
  username: string().required("Please enter your Username or Email!"),
  password: string().required("Please enter your password!"),
});

export default {
  name: "LoginComponent",
  components: {
    NormalHeading,
    ErrorModal,
    CustomButton,
  },
  methods: {
    async submit() {
      loginSchema
        .validate(this.form.values, { abortEarly: false })
        .then(async () => {
          this.form.errors = {
            username: "",
            password: "",
          };
          //client validation successful
          const response = await axios.post("http://localhost:8080/api/v1/user/signin", this.form.values);

          if (response.status == 200) {
            // get the token from the data from the server
            const responseData = await response.data;
            console.log("Login successful! " + JSON.stringify(responseData));
            // save the token in the browser for reuse
            localStorage.setItem("token", responseData.token);
            const decodedToken = jwtDecode(responseData.token);
            this.store.login({username: this.form.values.username, role: decodedToken.role, userId: decodedToken.userId});
            this.$router.push({ path: "/" });
          } else {
            const errorData = await response.data;
            console.log(JSON.stringify(errorData));
            this.errorMessage = "Error registering user: "+errorData.message;
            this.isErrorModalVisible = true;
          }
        })
        .catch((err) => {
          this.errorMessage = "Error registering user: " + err.message;
          this.isErrorModalVisible = true;
          if (err.inner && Array.isArray(err.inner)) {
            err.inner.forEach((error) => {
              if (this.form.errors[error.path]) {
                this.form.errors[error.path] = error.message;
              }
            });
          }
        });
    },
    validate(field) {
      loginSchema
        .validateAt(field, this.form.values)
        .then(() => {
          console.log("Success validating onBlur for field: " + field);
          this.form.errors[field] = "";
        })
        .catch((err) => {
          console.log(
            "Error while validating field " + field + " onBlur , " + err.message
          );
          this.form.errors[field] = err.message;
        });
    },
  },
  data() {
    return {
      form: {
        values: { username: "", password: "" },
        errors: { username: "", password: "" },
      },
      isErrorModalVisible: false,
      errorMessage: "",
      store: useCompleteStore()
    };
  },
};
</script>
