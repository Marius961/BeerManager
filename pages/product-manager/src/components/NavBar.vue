<template>
  <header >
    <div class="nav-container-bg container-fluid">
      <nav class="container p-0">
        <div class="row align-items-center">
          <div class="col-6 col-sm-auto" id="menuBtn">
            <div class="row">
              <div class="col-auto nav-menu-btn pt-1 pb-1">
                <div class="row align-items-center no-gutters">
                  <div class="col-auto">
                    <div class="menu-dot"></div>
                    <div class="menu-dot"></div>
                    <div class="menu-dot"></div>
                  </div>
                  <div class="col-auto mr-2">
                    <div class="menu-line"></div>
                    <div class="menu-line"></div>
                    <div class="menu-line"></div>
                  </div>
                  <div class="col brand-name d-none d-sm-block">Product Manager</div>
                  <div class="col brand-name d-sm-none">PM</div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-3 position-absolute" id="navMenu">
            <div class="row containe">
              <div class="col nav-menu">
                <a href="#" class="row p-2">
                  <div class="col-3 text-center">
                    <img src="../assets/img/nav-bar/home.png" width="24" height="24">
                  </div>
                  <div class="col pt-1">На головну</div>
                </a>
                <div class="row">
                  <div class="col">
                    <div class="row p-2 nav-menu-item">
                      <div class="col-3 text-center">
                        <img src="../assets/img/nav-bar/categories.png" width="24" height="24">
                      </div>
                      <div class="col">Категорії</div>
                    </div>
                    <div class="row">
                      <div class="col menu-item-content-wrapper">
                        <div class="row" id="categories">
                          <div class="col-12 category-item">
                            <div class="row p-2">
                              <div class="col-3 text-center">
                                <img src="../assets/img/test/category.png" width="32" height="32">
                              </div>
                              <div class="col">Пекарня</div>
                            </div>
                            <div class="row">
                              <div class="col category-item-content-wrapper">
                                <div class="row p-2">
                                  <a class="col-6 subcategory p-1" href="#">Хліб</a>
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
          </div>
          <div class="col-6 col-sm">
            <div class="row justify-content-end m-0"  style="flex-wrap: nowrap">
              <div class="col-auto nav-dropdown">
                <div class="row nav-dropdown-btn align-items-center">
                  <div class="col-auto">
                    <img src="../assets/img/nav-bar/search.png" alt="">
                  </div>
                </div>
              </div>
              <div class="col-auto nav-dropdown">
                <div class="row align-items-center">
                  <div class="col-2">
                    <img src="../assets/img/nav-bar/user-icon.png" alt="">
                  </div>
                </div>
                <div class="nav-dropdown-menu user-actions-dropdown mt-2">
                  <div class="row no-gutters">
                    <a class="col-12 p-2 pl-2" href="#">Моє меню</a>
                    <hr class="w-100 m-1">
                    <a class="col-12 p-2 pl-2" href="#">Вийти</a>
                  </div>
                </div>
              </div>
              <div class="col-auto nav-dropdown">
                <div class="row align-items-center">
                  <div class="col-2" style="position: absolute; z-index: 1; top: 11px; right: 14px">
                    <div class="row align-items-center">
                      <div class="col">
                        <div class="nav-notifications-count-icon">999</div>
                      </div>
                    </div>
                  </div>
                  <div class="col-2">
                    <img src="../assets/img/nav-bar/cart-icon.png" alt="">
                  </div>
                </div>
                <div class="nav-dropdown-menu cart-dropdown-menu mt-2">
                  <div class="row m-0">
                    <div class="col-12">
                      <div class="row nav-cart-box p-1">
                        <div class="col">
                          <div class="row nav-cart-item align-items-center p-3 mt-1 mb-1">
                            <div class="col-4 col-sm-2 text-center">
                              <img src="../assets/img/test/product-photo.png" alt="">
                            </div>
                            <div class="col-8 col-sm-10 align-self-center text-center nav-cart-item-text">Апельсин</div>
                            <hr class="w-100">
                            <div class="col">
                              <div class="row cart-item-info">
                                <div class="col-6 text-center">32кг</div>
                                <div class="col-6 text-center">19548 грн</div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <hr>
                    <div class="col-12 nav-cart-total-price p-2 mt-2 text-center">Сума: 99999грн</div>
                    <button class="col-12 nav-go-to-cart-btn p-2 mt-2 text-center">Перейти у кошик</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </header>
</template>

<script>
  export default {
    data() {
      return {

      }
    },
    methods: {
    }
  }
</script>

<style scoped>

</style>
