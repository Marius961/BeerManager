import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Cart from '../pages/Cart'
import Order from "../pages/Order"
import Error_404 from "../pages/404Error"

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
    },
    {
      path: '/404',
      component: Error_404
    }
  ]
})
