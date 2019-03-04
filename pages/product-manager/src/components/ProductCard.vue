<template>
  <div class="p-4">
    <div class="row justify-content-center product-card">
      <div class="col-12 card-img">
        <div class="row h-100 align-items-center no-gutters">
          <div class="col-12 text-center">
            <img :src="imgSrc" alt="">
          </div>
          <div v-if="isInStock" class="col-auto in-stock-label">В наявності</div>
        </div>
      </div>
      <div class="col-12 card-body align-self-start">
        <div class="row">
          <div class="col-12 product-name">{{name}}</div>
          <div class="col-12 seller-nick">Продавець: {{sellerName}}</div>
          <div class="col-12 pt-3">
            <div class="row align-items-end justify-content-between">
              <div class="col-auto">Ціна:</div>
              <div class="col-auto text-right product-price">{{priceForMeasurementUnit}} грн/{{measurementUnit.name}}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 align-self-end">
        <div class="row align-items-center justify-content-between pt-1 pb-2">
          <hr class="w-100 m-0 mt-1 mb-1">
          <router-link :to="'/product/' + id" class="col-auto details-btm">Деталі</router-link>
          <div class="col-auto text-center" @click="inFavorites = !inFavorites">
            <i class="fa-star in-favorites-icon" :class="{'far': !inFavorites, 'fas': inFavorites}"></i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      props: {
        id: Number,
        name: String,
        imgSrc: String,
        priceForMeasurementUnit: Number,
        measurementUnit: Object,
        sellerName: String,
        isInStock: Boolean,
        isInFavorites: Boolean
      },
      data() {
        return {
          inFavorites: this.isInFavorites
        }
      },
      watch: {
        inFavorites() {
          console.log('saved or removed from favorites')
        }
      }
    }
</script>

<style scoped>
  @import "../assets/css/product-card.css";
</style>
