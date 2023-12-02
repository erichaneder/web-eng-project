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
        <div
          class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start"
        >
          <div class="flex flex-shrink-0 items-center">
            <img
              class="h-16 w-auto -mt-6"
              src="@/assets/shoes.svg"
              alt="Sneaker Shop"
            />
          </div>
          <div class="hidden sm:ml-6 sm:block">
            <div class="flex space-x-4">
              <router-link
                v-for="item in navigation"
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
            <ShoppingCartIcon class="h-8 w-8" aria-hidden="true">
            </ShoppingCartIcon>
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
                <div v-if="isLoggedIn">
                  <MenuItem v-slot="{ active }">
                    <router-link
                      to="/profile"
                      :class="[
                        active ? 'bg-gray-100' : '',
                        'block px-4 py-2 text-sm text-gray-700',
                      ]"
                      >Your Profile</router-link
                    >
                  </MenuItem>
                  <MenuItem v-slot="{ active }">
                    <span :class="[active ? 'bg-gray-100' : '','block px-4 py-2 text-sm text-gray-700']" @click="logout()"
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
        { name: "Add Product", href: "/addProduct", current: false, requiresAdmin: true },
        { name: "Users", href: "/users", current: false, requiresAdmin: true },
        { name: "Orders", href: "/orders", current: false, requiresAdmin: true },
      ],
      isloggedIn: false,
      isAdmin: false
    };
  },
  methods: {
    checkLoggedIn() {
      return localStorage.getItem("userId") != null;
    },
    logout() {
      localStorage.removeItem("userId");
      localStorage.removeItem("role");
      localStorage.removeItem("token");
      this.isloggedIn = false;
      this.$router.push("/");
    },
    checkIsAdmin() {
      return localStorage.getItem("role") === "ROLE_ADMIN";
    }
  },
  mounted() {
    this.isLoggedIn = this.checkLoggedIn();
    this.isAdmin = this.checkIsAdmin();
  },
  computed: {
    filteredNavigation() {
      return this.navigation.filter(item => !item.requiresAdmin || this.isAdmin);
    }
  },
  watch: {
    $route() {
      this.navigation.forEach(item => {
        item.current = item.href === this.$route.path;
      });
      this.isLoggedIn = this.checkLoggedIn();
      this.isAdmin = this.checkIsAdmin();
      this.$forceUpdate(); //maybe useless, should force the component to re-render
    }
  },
};
</script>