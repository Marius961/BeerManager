import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Cart from '../pages/Cart'
import Order from "../pages/Order"
import Error_404 from "../pages/404Error"
import ProductPage from '../pages/product/productPage'
import ProductsByCategory from '../pages/product/productByCategory'
import AddProduct from '../pages/product/addProduct'
import AddCategory from '../pages/addCategory'
import MyOrders from '../pages/MyOrders'
import ReceivedOrders from '../pages/ReceivedOrders'

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
    },
    {
      path: '/add-product',
      component: AddProduct
    },
    {
      path: '/add-category',
      component: AddCategory
    },
    {
      path: '/my-orders',
      component: MyOrders
    },
    {
      path: '/received-orders',
      component: ReceivedOrders
    },
  ]
})
