<template>
  <main class="container">
    <div class="row seller-products" v-for="seller in sellersWithProducts" :key="seller.id">
      <span class="col-auto">Товари продавця</span>
      <div class="col-auto seller-name">{{seller.name}}</div>
      <div class="col-12">
        <div class="row product align-items-center" v-for="item in seller.productsList" :key="item.id">
          <div class="col-3 col-md-2 text-center oorder-1">
            <img :src="item.imageSrc" alt="">
          </div>
          <div class="col-6 col-md order-2">{{item.name}}</div>
          <hr class="w-100 d-md-none order-4">
          <div class="col-4 col-md-2 text-center order-4 order-md-3">
            <div class="row">
              <div class="col">{{item.priceForMeasurementUnit}} грн.</div>
              <div class="col-12">за {{item.measurementUnit}}</div>
            </div>
          </div>
          <div class="col-4 col-md-2 order-4">
            <label>
              <input
                @change="setQuantity({sellerId: seller.id, itemId: item.id, quantity: $event.target.value})"
                type="number" :value="item.quantity"
              >
            </label>
          </div>
          <div class="col-4 col-md-2 order-5">{{item.quantity * item.priceForMeasurementUnit}} грн.</div>
          <div class="col-2 col-md-1 text-center order-3 order-md-6">
            <img @click="deleteItemFromCart(item.id)" src="../assets/img/delete.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>

  import {mapGetters} from 'vuex'
  import {mapActions} from 'vuex'
  export default {
    computed: {
      ...mapGetters({
        sellersWithProducts: 'getSellersWithProducts'
      })
    },
    methods: {
      ...mapActions({
        setQuantity: 'updateItemQuantity',
        deleteItemFromCart: 'deleteItemFromCartById'
      })
    }
  }
</script>

<style scoped>
  @import "../assets/css/cart.css";
</style>
