import Vuex from 'vuex';
import axios from 'axios';

export default new Vuex.Store({
  state: {
    products: []
  },
  mutations: {
    SET_PRODUCTS(state, products) {
      state.products = products;
    }
  },
  actions: {
    async fetchProducts({ commit }) {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/product/list/');
        commit('SET_PRODUCTS', response.data);
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    }
  },
  getters: {
    getProductById: (state) => (id) => {
      return state.products.find(product => product.id === id);
    }
  }
});
