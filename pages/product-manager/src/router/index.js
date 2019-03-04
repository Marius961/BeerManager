import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Cart from '../pages/Cart'
import Order from "../pages/Order"
import Error_404 from "../pages/404Error"
import ProductPage from '../pages/product/productPage'
import ProductsByCategory from '../pages/productByCategory'

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
      path: '/product/:id',
      component: ProductPage
    },
    {
      path: '/404',
      component: Error_404
    },
    {
      path: '/products/:categoryId',
      component: ProductsByCategory
    }
  ]
})
