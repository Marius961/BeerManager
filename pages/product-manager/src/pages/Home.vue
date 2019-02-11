<template>
  <main class="container">
    <div class="row">
      <div class="col-12 mt-sm-3">
        <div class="row cards-bar">
          <a v-for="c in 6" href="#" class="col-7 col-sm-5 col-md-3 col-lg-2 category-card p-3">
            <div class="row align-items-start text-center">
              <div class="col">
                <img src="../assets/img/test/category.png" alt="">
              </div>
              <div class="col-12 mt-3">Пекарня</div>
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
                class="col-12 col-sm-6 col-md-4 col-xl-3"
                productName="Молоко"
                imgSrc="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdr8i-0nIWMk3EW39bbDrQMvX3ucvU8R9_Bb82i6Ui54qO2EUC"
                priceForMeasurementUnit="21,90"
                measurementUnit="л."
                sellerNickname="marius961"
                inStockStatus="true"
                isInFavorites="false"
              >
              </product-card>
              <product-card
                class="col-12 col-sm-6 col-md-4 col-xl-3"
                productName="Молоко"
                imgSrc="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqowTiH5C4Ywszo5AXBxX9NrcEedr3j1CY7-n2hf7SWkrAMv47DQ"
                priceForMeasurementUnit="21,90"
                measurementUnit="л."
                sellerNickname="marius961"
                inStockStatus="true"
                isInFavorites="false"
              >
              </product-card>
              <product-card
                class="col-12 col-sm-6 col-md-4 col-xl-3"
                productName="Молоко"
                imgSrc="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKtngGfEjZwPMFDFGOmxTeyrrObo-GE0Y44GSxKtdtINAZaL_P"
                priceForMeasurementUnit="21,90"
                measurementUnit="л."
                sellerNickname="marius961"
                inStockStatus="true"
                isInFavorites="false"
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
  import ProductCard from '../components/ProductCard'
    export default {
        components: {
          "product-card": ProductCard
        }
    }
</script>

<style scoped>

</style>
