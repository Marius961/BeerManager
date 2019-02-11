<template>
  <div class="p-4">
    <div class="row justify-content-center product-card">
      <div class="col-12 card-img">
        <div class="row h-100 align-items-center">
          <div class="col-12 text-center">
            <img :src="imgSrc" alt="">
          </div>
          <div v-if="inStockStatus" class="col-auto in-stock-label">В наявності</div>
        </div>
      </div>
      <div class="col-12 card-body align-self-start">
        <div class="row">
          <div class="col-12 product-name">{{productName}}</div>
          <div class="col-12 seller-nick">Продавець: {{sellerNickname}}</div>
          <div class="col-12 pt-3">
            <div class="row align-items-end justify-content-between">
              <div class="col-auto">Ціна:</div>
              <div class="col-auto text-right product-price">{{priceForMeasurementUnit}} грн/{{measurementUnit}}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 align-self-end">
        <div class="row align-items-center justify-content-between pt-1 pb-2">
          <hr class="w-100 m-0 mt-1 mb-1">
          <a href="#" class="col-auto details-btm">Деталі</a>
          <div class="col-auto text-center">
            <img src="../assets/img/heart.png" alt="">
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
        imgSrc: String,
        productName: String,
        priceForMeasurementUnit: Number,
        measurementUnit: String,
        sellerNickname: String,
        inStockStatus: Boolean,
        isInFavorites: Boolean
      }
    }
</script>

<style scoped>
  @import "../assets/css/product-card.css";
</style>
