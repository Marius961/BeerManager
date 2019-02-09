<template>
  <header >
    <div class="container-fluid nav-bar-container">
      <div class="container">
        <nav class="row align-items-center">
          <div class="col-5 col-sm-auto menu-btn-container"
               @click="showMenu"
               @mouseenter="showMenu"
               @mouseleave="hideMenu(menuHideDelay)">
            <div class="row h-100 align-items-center menu-button" style="z-index: 99" @click="hideMenu(0)">
              <div class="col-auto">
                <div class="row align-items-center no-gutters">
                  <div class="col-auto">
                    <div v-for="dot in 3" class="menu-dot"></div>
                  </div>
                  <div class="col-auto mr-2">
                    <div v-for="line in 3" class="menu-line"></div>
                  </div>
                  <div class="col brand-name d-none d-sm-block">Product Manager</div>
                  <div class="col brand-name d-sm-none">PM</div>
                </div>
              </div>
            </div>
            <div class="row pr-3 position-absolute" style="pointer-events: none">
              <div class="col menu-container" :class="{'show-menu': isShowMenu}">
                <div class="row">
                  <div class="col-12 nav-menu">
                    <div class="row menu-links-container align-items-center pt-2 pb-2">
                      <a href="#" class="col-3 col-sm-2">
                        <div class="row justify-content-center">
                          <div class="col-auto text-center">
                            <img src="../assets/img/nav-bar/home.png" width="24" height="24">
                          </div>
                        </div>
                      </a>
                      <span class="col font-weight-bold fs-4" style="border-left: 1px solid white">Меню</span>
                    </div>
                    <div class="row">
                      <div class="col">
                        <div class="row menu-item" @click="showCategories = !showCategories">
                          <div class="col">
                            <div class="row align-items-center">
                              <div class="col-9 text-left">Категорії</div>
                              <div class="col-3">
                                <img class="dropdonw-arrow" :class="{'dropdonw-arrow-opened': showCategories}" src="../assets/img/nav-bar/dropdown_arrow.png" alt="">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row dropdown-content" :class="{'show-dropdown-content': showCategories}">
                          <div class="col-12" v-for="category in categories" @click="category.isOpened = !category.isOpened">
                            <div class="row category align-items-center">
                              <div class="col-auto p-1">
                                <img src="../assets/img/test/category.png">
                              </div>
                              <div class="col">{{category.name}}</div>
                            </div>
                            <div class="row subcategories" v-if="category.isOpened">
                              <a class="col-12 col-sm-6" v-for="subcategory in category.subcategories" :href="subcategory.id">{{subcategory.name}}</a>
                            </div>
                          </div>
                        </div>
                      </div>
                      <a class="col-12">
                        <div class="row menu-item">
                          <a href="#" class="col">Про нас</a>
                        </div>
                      </a>
                      <a class="col-12">
                        <div class="row menu-item">
                          <a href="#" class="col">Оплата і доставка</a>
                        </div>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-7 col-sm">
            <div class="row justify-content-end m-0"  style="flex-wrap: nowrap">
              <div class="col-auto nav-dropdown">
                <div class="row h-100 align-items-center">
                  <div class="col-auto">
                    <img src="../assets/img/nav-bar/search.png" alt="">
                  </div>
                </div>
              </div>
              <div class="col-auto">
                <div class="row align-items-center nav-dropdown">
                  <div class="col-2">
                    <img src="../assets/img/nav-bar/user-icon.png" alt="">
                  </div>
                </div>
                <div class="nav-dropdown-menu user-actions-dropdown">
                  <div class="row no-gutters">
                    <a class="col-12" href="#">Мої замовлення</a>
                    <a class="col-12" href="#">Отримані замовлення</a>
                    <a class="col-12" href="#">Мої товари</a>
                    <hr class="w-100 m-1">
                    <a class="col-12" href="#">Вийти</a>
                  </div>
                </div>
              </div>
              <div class="col-auto">
                <div class="row align-items-center nav-dropdown">
                  <div class="cart-items-count">933</div>
                  <div class="col-2">
                    <img src="../assets/img/nav-bar/cart-icon.png" alt="">
                  </div>
                </div>
                <div class="nav-dropdown-menu cart-dropdown-menu">
                  <div class="row m-0">
                    <div class="col-12">
                      <div class="row nav-cart-box p-1">
                        <div class="col">
                          <div v-for="item in cartItems" class="row nav-cart-item align-items-center p-3 mt-1 mb-1">
                            <div class="col-4 col-sm-2 text-center">
                              <img :src="item.imageSrc" alt="">
                            </div>
                            <div class="col-8 col-sm-10 align-self-center text-center nav-cart-item-text">{{item.name}}</div>
                            <hr class="w-100">
                            <div class="col">
                              <div class="row cart-item-info">
                                <div class="col-6 text-center">{{item.quantity}}{{item.measurementUnit.shortName}}</div>
                                <div class="col-6 text-center">{{item.priceForOneMeasurementUnit * item.quantity}} грн</div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <hr>
                    <div class="col-12 nav-cart-total-price p-2 mt-2 text-center">Сума: {{calculateCartSum()}}грн</div>
                    <button class="col-12 nav-go-to-cart-btn p-2 mt-2 text-center">Перейти у кошик</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </div>
  </header>
</template>

<script>
  let carIte1 = {
    id: 0,
    imageSrc: 'https://wp.miray.com.ua/site/miray/files/wallpapers/wallpaper-4187.jpg',
    name: 'Апельсин',
    priceForOneMeasurementUnit: 15.60,
    quantity: 3,
    measurementUnit: { id:0, shortName: 'кг', fullName: 'кілограм'},
  };
  let carIte2 = {
    id: 1,
    imageSrc: 'https://irecommend.ru/sites/default/files/product-images/42233/zwr0obbJCnszZYssy91zA.jpg',
    name: 'Мука',
    priceForOneMeasurementUnit: 20,
    quantity: 2,
    measurementUnit: { id:0, shortName: 'кг', fullName: 'кілограм'},
  };
  let carIte3 = {
    id: 2,
    imageSrc: 'https://i.biz-gid.com/img/products/800/216796.png',
    name: 'Хліб',
    priceForOneMeasurementUnit: 8.50,
    quantity: 2,
    measurementUnit: { id:0, shortName: 'шт', fullName: 'штук'},
  };
  let carIte4 = {
    id: 2,
    imageSrc: 'https://img2.zakaz.ua/src.1470729330.ad72436478c_2016-08-09_Aleksey/src.1470729330.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.1350nowm.jpg.1350x.jpg',
    name: 'Моршинська (сильногазована)',
    priceForOneMeasurementUnit: 12.50,
    quantity: 1,
    measurementUnit: { id:0, shortName: 'л.', fullName: 'літрів'},
  };

  let category = {
    id: 0,
    name: 'Пекарня',
    isOpened: false,
    subcategories: [
      {
        id: 0,
        name: 'Хліб',
      },
      {
        id: 1,
        name: 'Хлібці',
      }
    ]
  }
  let category2 = {
    id: 2,
    name: 'Бакалія',
    img: '',
    isOpened: false,
    subcategories: [
      {
        id: 0,
        name: 'Макарони',
      },
      {
        id: 1,
        name: 'Замороженные хлебобулочные изделия',
      }
    ]
  }

  export default {
    data() {
      return {
        cartItems: [carIte1, carIte2, carIte3, carIte4],
        cartSum: 0,
        showCategories: false,
        categories: [category, category2],
        isShowMenu: false,
        menuCloseTimerId: '',
        menuHideDelay: 300
      }
    },
    methods: {
      calculateCartSum() {
        let sum = 0;
        this.cartItems.map(item => {
          sum += item.priceForOneMeasurementUnit * item.quantity
        });
        return sum;
      },
      showMenu() {
        this.isShowMenu = true;
        clearTimeout(this.menuCloseTimerId);
      },
      hideMenu(delay) {
        this.menuCloseTimerId = setTimeout(() => {
          this.isShowMenu = false;
          this.showCategories = false;
          this.hideAllSubcategories();
        }, delay)
      },
      hideAllSubcategories() {
        this.categories.map(category => {
          category.isOpened = false;
        })
      }
    }
  }
</script>

<style scoped>
  @import "../assets/css/nav-bar.css";


</style>
