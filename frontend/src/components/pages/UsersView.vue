<template>
    <div class="container mx-auto my-8 px-4 mb-40">
      <NormalHeading text="All Users"/>
      <div class="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
        <table class="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
          <thead>
            <tr class="text-center">
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">ID</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Salutation</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Name</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Email</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Country</th>
              <th class="py-2 px-3 sticky top-0 border-b bg-teal-700 text-white">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id" class="hover:bg-gray-100">
              <td class="py-2 px-3 border-b border-gray-200">{{ user.id }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ user.salutation }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ user.name }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ user.email }}</td>
              <td class="py-2 px-3 border-b border-gray-200">{{ user.address.country }}</td>
              <td class="py-2 px-3 border-b border-gray-200">
                <div v-if="this.store.getUserId != user.id">
                  <CustomButton @clicked="editUser(user.id)" customButtonStyle="p-2 hover:bg-green-500">
                      <PencilIcon class="h-5 w-5 text-green-400 hover:text-white" />
                  </CustomButton>
                  <CustomButton @clicked="deleteUser(user.id)" customButtonStyle="p-2 hover:bg-red-500">
                      <TrashIcon class="h-5 w-5 text-red-400 hover:text-white" />
                  </CustomButton>
              </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import { TrashIcon, PencilIcon } from '@heroicons/vue/24/outline'
  import CustomButton from '@/components/atoms/Button.vue';
  import NormalHeading from "@/components/atoms/NormalHeading.vue";
  import { useCompleteStore } from "@/store/store";
  
  export default {
    components: {
        TrashIcon,
        CustomButton,
        PencilIcon,
        NormalHeading
    },
    data() {
      return {
        users: [],
        store: useCompleteStore()
      };
    },
    methods: {
        async deleteUser(userId) {
            await this.store.deleteUser(userId);
            this.users = this.store.getAllUsers;
        },
        editUser(userId) {
            this.$router.push({ path: '/user/' + userId });
        }
    },
    async mounted() {
      await this.store.fetchUsers();
      this.users = this.store.getAllUsers; 
    }
  };
  </script>
  