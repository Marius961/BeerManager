<template>
  <main class="container">
    <div class="row">
      <div class="col-12 mt-sm-3">
        <div class="row cards-bar">
          <a class="col-7 col-sm-5 col-md-3 col-lg-2 category-card p-3" v-for="category in categories">
            <div class="row align-items-start text-center">
              <div class="col">
                <img src="../assets/img/test/category.png" alt="">
              </div>
              <div class="col-12 mt-3">{{category.name}}</div>
            </div>
          </a>
        </div>
      </div>
      <div  class="col-12 mt-3 p-2 pl-3 pr-3">
        <div class="row">
          <div class="col-12 col-sm-auto font-weight-bold">Популярні товари в категорії &laquo;пекарня&raquo;</div>
          <a href="#" class="col link-1 text-right font-weight-light">Перейти до категорії</a>
          <hr class="w-100 m-1">
          <div class="col-12">
            <div class="row">
              <product-card
                v-for="product in products" :key="product.id"
                class="col-12 col-sm-6 col-md-4 col-xl-3"
                :id="product.id"
                :productName="product.name"
                :imgSrc="product.imgSrc"
                :priceForMeasurementUnit="product.priceForMeasurementUnit"
                :measurementUnit="product.measurementUnit"
                :sellerName="product.sellerName"
                :isInStock="product.isInStock"
                :isInFavorites="product.isInFavorites"
              >
              </product-card>
            </div>
          </div>
          <a href="#" class="col link-1 text-right font-weight-light">показати більше</a>
          <hr class="w-100 m-1">
        </div>
      </div>
    </div>
  </main>
</template>

<script>

  let product1 = {
    id: 0,
    name: 'Молоко',
    imgSrc: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKtngGfEjZwPMFDFGOmxTeyrrObo-GE0Y44GSxKtdtINAZaL_P',
    priceForMeasurementUnit: 22.90,
    measurementUnit: 'л,',
    sellerName: 'marius961',
    isInStock: true,
    isInFavorites: false

  };

  import ProductCard from '../components/ProductCard'

  export default {
    computed: {
      categories() {
        return this.$store.getters.getCategoriesMap
      }
    },
    data() {
      return {
        products: [product1]
      }
    },
    components: {
      "product-card": ProductCard
    },
  }
</script>

<style scoped>

</style>
