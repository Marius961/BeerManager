<template>
  <main class="container">
    <div class="row">
      <div class="col-12 mt-sm-3" id="categoriesList">
        <div class="row" style="overflow-x: auto; flex-wrap: nowrap">
          <a href="#" class="col-7 col-sm-5 col-md-3 col-lg-2 category-card p-3">
            <div class="row align-items-start text-center">
              <div class="col">
                <img src="../assets/img/test/category.png" alt="">
              </div>
              <div class="col-12 mt-3">Пекарня</div>
            </div>
          </a>
        </div>
      </div>
      <div class="col-12 box mt-3 p-2 pl-3 pr-3">
        <div class="row">
          <div class="col-12 col-sm-auto font-weight-bold">Популярні товари в категорії &laquo;пекарня&raquo;</div>
          <a href="#" class="col link-1 text-right font-weight-light">Перейти до категорії</a>
          <hr class="w-100 m-1">
          <div class="col-12">
            <div class="row no-gutters row-eq-height" id="products-container" style="overflow-x: auto; overflow-y: hidden; flex-wrap: nowrap">
              <div class="col-12 col-sm-6 col-md-4 col-xl-3 product-card p-2 p-xl-0">
                <div class="row pb-3 no-gutters">
                  <div class="col-12 p-3">
                    <div class="col-12 text-center pt-2 product-name">Апельсин</div>
                  </div>
                  <div class="col-12 align-self-end">
                    <div class="row justify-content-center no-gutters">
                      <div class="col-auto">
                        <img class="product-picture" src="https://st.depositphotos.com/1000141/1936/i/950/depositphotos_19366981-stock-photo-one-ripe-orange-in-closeup.jpg">
                      </div>
                      <div class="w-100"></div>
                      <div class="col-8 col-sm-10 col-lg-9 col-xl-10 mt-4">
                        <div class="row no-gutters justify-content-center">
                          <div class="col-12 col-sm-9 price-box">
                            <div class="row no-gutters p-2">
                              <span class="col-auto text-right align-self-center">99999.99</span>
                              <span class="col-auto price-currency text-left pl-1">грн/кг</span>
                            </div>
                          </div>
                          <div class="col-12 col-sm-3 to-cart-segment">
                            <div class="row no-gutters p-1 p-lg-2 h-100 align-items-center">
                              <div class="col text-center">
                                <img src="../assets/img/to_cart_icon_white.png" height="24" width="24">
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <hr class="w-100 m-1">
          <a href="#" class="col link-1 text-right font-weight-light">показати більше</a>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>

</style>
