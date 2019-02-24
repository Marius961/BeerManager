import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Cart from '../pages/Cart'
import Order from "../pages/Order"

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
    },
    {
      path: '/create-order',
      component: Order
    }
  ]
})
