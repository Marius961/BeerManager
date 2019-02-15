import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Cart from '../pages/Cart'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/cart',
      component: Cart
    }
  ]
})
