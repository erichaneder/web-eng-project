<template>
  <div class="min-h-screen flex flex-col items-center px-5">
    <div class="w-96 bg-white p-8 rounded shadow mt-20">
      <NormalHeading text="Login" />
      <div class="h-0.5 bg-gray-200 w-36 mx-auto mt-2.5 mb-4"></div>
      <form @submit.prevent="submit">
        <div class="mb-4">
          <label for="email" class="block text-sm mb-1">Email:</label>
          <input
            type="email"
            id="email"
            class="w-full h-10 px-2 border rounded"
            v-model="form.values.email"
            @blur="validate('email')"
          />
          <p class="text-red-500" v-if="!!form.errors.email">
            {{ form.errors.email }}
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
//import axios from 'axios';
import ErrorModal from "@/components/atoms/ErrorModal.vue";
import { object, string } from "yup";

const loginSchema = object().shape({
  email: string().required().email(),
  password: string().required(),
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
            email: "",
            password: "",
          };
          //client validation successful

          const response = await fetch("http://localhost:8080/api/v1/signin", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(this.form.values),
          });

          if (response.ok) {
            console.log("Login successful!");
            // get the token from the data from the server
            const responseData = await response.json();
            const token = responseData.token;
            // save the token in the browser for reuse
            localStorage.setItem("token", token);

            const userRole = responseData.userRole; // Assuming 'userRole' is the key in your response
            sessionStorage.setItem("role", userRole);
            this.$router.push({ path: "/" });
          } else {
            if (Object.keys(response).length === 0) {
              //Response is leer -> wsh 403 Forbidden
              this.errorMessage =
                "An error occurred. Please try again later. (" +
                response.status +
                " " +
                response.statusText +
                ")";
            } else {
              const errorData = response.json();
              this.errorMessage =
                "Error registering user: " + errorData.message;
            }
            this.isErrorModalVisible = true;
          }
        })
        .catch((err) => {
          console.error("Error occurred:", err.message);
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
        values: { email: "", password: "" },
        errors: { email: "", password: "" },
      },
      isErrorModalVisible: false,
      errorMessage: "",
    };
  },
};
</script>
