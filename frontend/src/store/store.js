import { defineStore } from "pinia";
import axios from 'axios';

export const useCompleteStore = defineStore('complete', {
    state() {
      return {
        products: [],
        basketItems: [],
        isProductsFetched: false,
        fetchError: null,
        user: {
          username: '',
          userId: null,
          role: ''
        },
        users: [],
        orders: [],
        profile: null,
        profileError: null
      }
    },
    actions: {
      addToBasket(item) {
        console.log(JSON.stringify(item));
        this.basketItems.push(item);
      },
      removeFromBasket(itemId) {
        const index = this.basketItems.findIndex(item => item.id === itemId);
        if (index !== -1) {
            this.basketItems.splice(index, 1);
        } 
      },
      async fetchProducts() {
        if (this.isProductsFetched) return;

        try {
          const response = await axios.get('http://localhost:8080/api/v1/product/list/');
          this.products = response.data.map(product => {
            return {
              id: product.id,
              name: product.name,
              description: product.description,
              price: product.price,
              image: require(`@/assets/${product.image}`)
            };
          });
          this.isProductsFetched = true;
          this.fetchError = null;
        } catch (error) {
          console.error('Error fetching products:', error);
          this.fetchError = 'Error fetching products:' + error;
          this.isProductsFetched = false;
        }
      },
      async fetchUsers() {
        const token = localStorage.getItem('token');
        const config = {
          headers: { Authorization: `Bearer ${token}` }
        };
        try {
          const response = await axios.get('http://localhost:8080/api/v1/user/list/', config); 
          this.users = response.data;
          console.log("Setting users to: " + response.data);
        } catch (error) {
          console.log('Error fetching users:', error);
        }
      },
      async fetchProfileData() {
        const token = localStorage.getItem('token');
        const config = {
          headers: { Authorization: `Bearer ${token}` }
        };
        try {
          const response = await axios.get('http://localhost:8080/api/v1/user/' + this.user.userId, config);
          const userData = response.data;
          this.profile = {
          ...userData, //-> ... = Spread Operator so kann man das object aufspreaden in individuelle Elemente, hier sagt man halt nimm alle Properties von product, und nacher wird das image dann umgsetzt und es werden noch extras hinzugefügt
          phone: userData.phonenumber,
          zipcode: userData.address.zipcode,
          street: userData.address.street,
          city: userData.address.city,
          country: userData.address.country,
        };
        } catch (error) {
          console.error('Error fetching user:', error);
        }
      },
      async updateProfileData(userId, payload) {
        const token = localStorage.getItem('token');
        const config = {
        headers: { Authorization: `Bearer ${token}` }
        };
        try {
          const response = await axios.put('http://localhost:8080/api/v1/user/update/' + userId, payload, config);
          if (response.status === 200) {
              console.log('User updated successfully:', response.data);
              this.profile = {...response.data };
          } else {
              console.error('Error updating user:', response.status, response.statusText);
              this.profileError = "Error updating user: " + response.status + " " +response.statusText;
          }
        } catch (error) {
            console.error('Network error:', error);
            this.profileError = "Error updating user: " + error.message;
        }
      },
      async fetchOrders() {
        const token = localStorage.getItem('token');
        const config = {
          headers: { Authorization: `Bearer ${token}` }
        };
        try {
          const response = await axios.get('http://localhost:8080/api/v1/order/list/', config); 
          this.orders = response.data;
        } catch (error) {
          console.error('Error fetching orders:', error);
        }
      },
      login (payload) {
        if(payload.username.includes("@")) {
          this.user.username = payload.username.split('@')[0];
        } else {
          this.user.username = payload.username;
        }
        this.user.userId = payload.userId;
        this.user.role = payload.role;
        console.log("UserName stored: " + this.user.username);
        console.log("UserId stored: " + this.user.userId);
        console.log("Role stored: " + this.user.role);
      },
      logout() {
        this.user.username = '';
        this.user.userId = null;
        this.user.role = '';
      },
      async deleteUser(userId) {
        console.log("Trying to delete user: " + userId);
        const token = localStorage.getItem('token');
        const config = {
          headers: { Authorization: `Bearer ${token}` }
        };
        console.log("Trying to delete wit this token: " + token);
        try {
          const response = await axios.delete('http://localhost:8080/api/v1/user/delete/' + userId, config); 
          console.log("Deleting successful: Response: " + response.data);
        } catch (error) {
          console.error('Error deleting user:', error);
        }
        const index = this.users.findIndex(item => item.userId === userId);
        if (index !== -1) {
            this.users.splice(index, 1);
        } 
      },
      async deleteOrder(orderId) {
          try {
              const response = await axios.delete('http://localhost:8080/api/v1/order/delete/' + orderId); 
              //logic here when delete successful
              console.log("Response: " + response);
          } catch (error) {
              console.error('Error deleting order:', error);
          }
      },
      getProductById(id) {
        return this.products.find(product => product.id === id);
      },
      checkforProfileError() {
        if(this.profileError) {
          var error = this.profileError;
          this.profileError = null;
          return error;
        }
        return null;
      }
    },
    getters: {
      isLoggedIn () {
        return this.user.username !== '' && this.userId !== '';
      },
      isAdmin() {
        return this.user.role === "ROLE_ADMIN";
      },
      getAllUsers() {
        console.log("Getting all users...");
        if(this.user.role === "ROLE_ADMIN") {
          return this.users;
        }
      },
      getAllProducts() {
        if(!this.isProductsFetched) {
          this.fetchProducts();
        }
        return this.products;
      },
      getAllOrders() {
        if(this.user.role === "ROLE_ADMIN") {
          return this.orders;
        }
      },
      getUserId() {
        return this.user.userId;
      },
      getProfileData() {
        return this.profile;
      },
      getBasketItems() {
        return this.basketItems;
      },
      getBasketTotal() {
        console.log(this.basketItems);
        return this.basketItems.reduce((total, item) => total + parseFloat(item.price), 0).toFixed(2);
      }
    },
});
