import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);
// test
//
import categories from './modules/categories'
import cart from './modules/cart'

export default new Vuex.Store({
  modules: {
    categories,
    cart
  }
})
