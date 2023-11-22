import Vuex from 'vuex';
import axios from 'axios';

export default new Vuex.Store({
  state: {
    products: [],
    basketItems: []
  },
  mutations: {
    SET_PRODUCTS(state, products) {
      state.products = products;
    },
    ADD_TO_BASKET(state, item) {
      state.basketItems.push(item);
    },
    REMOVE_FROM_BASKET(state, itemId) {
        const index = state.basketItems.findIndex(item => item.id === itemId);
        if (index !== -1) {
            state.basketItems.splice(index, 1);
        }
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
    },
    addToBasket({ commit }, item) {
      commit('ADD_TO_BASKET', item);
    },
    removeFromBasket({ commit }, itemId) {
        commit('REMOVE_FROM_BASKET', itemId);
    }
  },
  getters: {
    getProductById: (state) => (id) => {
      return state.products.find(product => product.id === id);
    },
    basketTotal: state => {
      return state.basketItems.reduce((total, item) => total + item.price, 0).toFixed(2);
  }
  }
});
