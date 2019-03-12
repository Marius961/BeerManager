<template>
  <main class="container">
    <div class="row seller-products" v-for="seller in sellersWithProducts" :key="seller.id">
      <span class="col-auto">Товари продавця</span>
      <div class="col-auto sku-number">{{seller.name}}</div>
      <div class="col-12">
        <div class="row no-gutters product align-items-center" v-for="cartItem in seller.cartItems" :key="cartItem.id">
          <div class="col-auto text-center">
            <label class=" checkbox-container">
              <input
                type="checkbox"
                :checked="cartItem.isReadyToOrder"
                @change="setIsReadyToOrderStatus($event, cartItem.id)"
              >
              <span class="checkmark"></span>
            </label>
          </div>
          <div class="col-auto text-center">
            <img :src="cartItem.image" alt="">
          </div>
          <div class="col order-3 order-md-2 pl-1 pr-1 product-name">{{cartItem.name}}</div>
          <hr class="w-100 d-md-none order-4">
          <div class="col-4 col-md-2 col-xl-1 text-center order-4 order-md-3 pl-1 pr-1">
            <span class="price">{{cartItem.priceForMeasurementUnit}}</span>
            <br>
            <span class="font-weight-bold">грн./{{cartItem.measurementUnit.name}}</span>
          </div>
          <div class="col-3 col-md-2 col-xl-1 order-4">
            <label>
              <input
                @change="updateItemQuantity($event, cartItem.id)"
                type="number" :value="cartItem.quantity"
              >
            </label>
          </div>
          <div class="col-5 col-md-2 col-xl-1 text-center order-5 pl-1 pr-1">
            <span class="price">{{cartItem.quantity * cartItem.priceForMeasurementUnit}}</span>
            <br>
            <span class="font-weight-bold">грн.</span>
          </div>
          <div class="col-auto text-center order-3 order-md-6 remove-btn">
            <img @click="deleteItemFromCart(cartItem.id)" src="../assets/img/delete.png" alt="">
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-sm-end" v-if="sellersWithProducts.length > 0">
      <router-link tag="button" to="/create-order" class="col-12 col-sm-auto  create-order-btn" :disabled="isReadyToOrder">Створити замовлення</router-link>
    </div>
  </main>
</template>

<script>

  import {mapGetters} from 'vuex'
  import {mapActions} from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        sellersWithProducts: 'getSellersWithProducts',
        isReadyToOrder: 'isItemsReadyToOrder'
      }),
    },
    methods: {
      ...mapActions({
        setQuantity: 'updateItemQuantity',
        deleteItemFromCart: 'deleteItemFromCartById',
        fetchCartSellersList: 'fetchCartSellersList',
        updateIsReadyToOrder: 'updateIsReadyToOrder'
      }),
      updateItemQuantity(event, itemId) {
        let quantity = parseInt(event.target.value);
        if (quantity > 0 && typeof quantity === 'number') {
          this.setQuantity({itemId: itemId, itemQuantity: quantity});
        } else {
          this.setQuantity({itemId: itemId, itemQuantity: 1});
          event.target.value = 1
        }
      },
      setIsReadyToOrderStatus(event, cartItemId) {
        this.updateIsReadyToOrder({itemId: cartItemId, isReadyToOrder: event.target.checked});
      }
    },
    created() {
      this.fetchCartSellersList();
    }
  }
</script>

<style scoped>
  @import "../assets/css/cart.css";
</style>
