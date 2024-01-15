<template>
  <Disclosure as="nav" class="bg-teal-900" v-slot="{ open }">
    <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
      <div class="relative flex h-6 items-center justify-between">
        <div class="absolute inset-y-0 left-0 flex items-center sm:hidden">
          <!-- Mobile menu button-->
          <DisclosureButton
            class="relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white"
          >
            <span class="absolute -inset-0.5"></span>
            <span class="sr-only">Open main menu</span>
            <Bars3Icon v-if="!open" class="block h-6 w-6" aria-hidden="true" />
            <XMarkIcon v-else class="block h-6 w-6" aria-hidden="true" />
          </DisclosureButton>
        </div>
        <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
          <div class="flex flex-shrink-0 items-center">
            <router-link to="/">
              <img class="h-16 w-auto -mt-6" src="@/assets/shoes.svg" alt="Sneaker Shop Logo" />
            </router-link>
          </div>
          <div class="hidden sm:ml-6 sm:block">
            <div class="flex space-x-4">
              <router-link
                v-for="item in filteredNavigation"
                :key="item.name"
                :to="item.href"
                :class="[
                  item.current
                    ? 'bg-gray-900 text-white'
                    : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                  'rounded-md px-3 py-2 text-sm font-medium',
                ]"
                :aria-current="item.current ? 'page' : undefined"
                >{{ item.name }}
              </router-link>
              <!-- Conditional rendering for Admin Panel -->
            <Menu v-if="isAdmin()" as="div" class="relative ml-3">
              <MenuButton class="inline-flex items-center rounded-md px-3 py-2 text-gray-300 hover:bg-gray-700 hover:text-white focus:outline-none">
                Admin Panel
              </MenuButton>

              <MenuItems class="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                <MenuItem v-slot="{ active }">
                  <router-link
                    to="/adminProducts"
                    :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                  >
                    Maintain Products
                  </router-link>
                </MenuItem>
                <MenuItem v-slot="{ active }">
                  <router-link
                    to="/addProduct"
                    :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                  >
                    Add Product
                  </router-link>
                </MenuItem>
                <MenuItem v-slot="{ active }">
                  <router-link
                    to="/users"
                    :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                  >
                    Users
                  </router-link>
                </MenuItem>
                <MenuItem v-slot="{ active }">
                  <router-link
                    to="/orders"
                    :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                  >
                    Orders
                  </router-link>
                </MenuItem>
              </MenuItems>
            </Menu>
            </div>
          </div>
        </div>
        <div
          class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"
        >
          <router-link
            to="/basket"
            class="relative rounded-full p-1 text-gray-400 hover:text-white hover:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
          >
            <span class="absolute -inset-1.5"></span>
            <span class="sr-only">View shopping cart</span>
            <ShoppingCartIcon class="h-8 w-8" aria-hidden="true"></ShoppingCartIcon>
              <span v-if="store.basketItems.length" class="absolute top-0 right-0 inline-flex items-center justify-center px-2 py-1 text-xs font-bold leading-none text-red-100 transform translate-x-1/2 -translate-y-1/2 bg-red-600 rounded-full">
                {{ store.basketItems.length }}
              </span>
          </router-link>

          <!-- Profile dropdown -->
          <Menu as="div" class="relative ml-3">
            <div>
              <MenuButton class="relative flex rounded-full text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 hover:bg-gray-800">
                <span class="absolute -inset-1.5"></span>
                <span class="sr-only">Open user menu</span>
                <UserIcon class="h-9 w-9 text-gray-400" />
              </MenuButton>
            </div>
              <MenuItems class="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                <div v-if="isLoggedIn()">
                  <MenuItem v-slot="{ active }">
                    <router-link to="/profile" :class="[active ? 'bg-gray-100' : '','block px-4 py-2 text-sm text-gray-700',]">Your Profile</router-link>
                  </MenuItem>
                  <MenuItem v-slot="{ active }">
                    <router-link to="/orders" :class="[active ? 'bg-gray-100' : '','block px-4 py-2 text-sm text-gray-700',]">Your Orders</router-link>
                  </MenuItem>
                  <MenuItem v-slot="{ active }">
                    <span :class="[active ? 'bg-gray-100 logout' : '','block px-4 py-2 text-sm text-gray-700 logout']" @click="logout()"
                      >Logout
                    </span>
                </MenuItem>
                </div>
                <div v-else>
                  <MenuItem v-slot="{ active }">
                    <router-link
                      to="/login"
                      :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                      >Log in</router-link
                    >
                  </MenuItem>
                  <MenuItem v-slot="{ active }">
                    <router-link
                      to="/register"
                      :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                      >Sign up</router-link
                    >
                  </MenuItem>
                </div>
              </MenuItems>
          </Menu>
        </div>
      </div>
    </div>

    <DisclosurePanel class="sm:hidden">
      <div class="space-y-1 px-2 pb-3 pt-2">
        <DisclosureButton
          v-for="item in navigation"
          :key="item.name"
          as="a"
          :href="item.href"
          :class="[
            item.current
              ? 'bg-gray-900 text-white'
              : 'text-gray-300 hover:bg-gray-700 hover:text-white',
            'block rounded-md px-3 py-2 text-base font-medium',
          ]"
          :aria-current="item.current ? 'page' : undefined"
          >{{ item.name }}</DisclosureButton
        >
      </div>
    </DisclosurePanel>
  </Disclosure>
</template>

<script>
import { Disclosure, DisclosureButton, DisclosurePanel, Menu, MenuButton, MenuItem,  MenuItems} from "@headlessui/vue";
import { Bars3Icon, ShoppingCartIcon, XMarkIcon, UserIcon} from "@heroicons/vue/24/outline";
import { useCompleteStore } from "@/store/store";

export default {
  name: "NavbarComponent",
  components: {
    Disclosure, DisclosureButton, DisclosurePanel, Menu, MenuButton, MenuItem,  MenuItems,
    Bars3Icon, ShoppingCartIcon, XMarkIcon, UserIcon
  },
  data() {
    return {
      navigation: [
        { name: "Home", href: "/", current: false },
        { name: "Products", href: "/products", current: false },
        { name: "About", href: "/about", current: false },
        { name: "Contact", href: "/contact", current: false },
        { name: "Help", href: "/help", current: false },
        { name: "Imprint", href: "/imprint", current: false },
      ],
      store: useCompleteStore()
    };
  },
  methods: {
    isLoggedIn() {
      return this.store.isLoggedIn;
    },
    logout() {
      localStorage.removeItem("token");
      this.store.logout();
      this.$router.push("/");
    },
    isAdmin() {
      return this.store.isAdmin;
    }
  },
  computed: {
    filteredNavigation() {
      return this.navigation.filter(item => !item.requiresAdmin || this.isAdmin());
    }
  }
};
</script>

<style>
  .logout {cursor: pointer; color: red; font-weight: bold;}
</style>
