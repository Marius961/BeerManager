<template>
  <header>
    <div class="container-fluid nav-bar-container">
      <div class="container">
        <nav class="row align-items-center">
          <div class="col-5 col-sm-auto menu-btn-container"
               @click="toggleSidebarVisibility"
          >
            <div class="row h-100 align-items-center" style="z-index: 99">
              <div class="col-auto">
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
          <div class="col-7 col-sm">
            <div class="row justify-content-end m-0"  style="flex-wrap: nowrap">
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
                              <div class="row">
                                <div class="col-6 text-center">{{item.quantity}}{{item.measurementUnit}}</div>
                                <div class="col-6 text-center">{{item.priceForMeasurementUnit * item.quantity}} грн</div>
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
    <div class="sidebar-wrapper"
         :class="{'show-sidebar-wrapper': isShowSideBar}"
    >
      <div class="container h-100" id="sidebar" @click.self="closeSideBar">
        <div class="sidebar" :class="{'show-sidebar': isShowSideBar}">
          <div class="row no-gutters">
            <div class="col-12">
              <div class="row menu-title-bar align-items-center">
                <a href="#" class="col-2 pb-2">
                  <img src="../assets/img/nav-bar/home.png" alt="">
                </a>
                <div class="col">
                  <h4>Меню</h4>
                </div>
                <div class="col-2 pb-2 d-md-none" @click="closeSideBar">
                  <img src="../assets/img/nav-bar/close.png" alt="">
                </div>
              </div>
            </div>
            <hr class="w-100 m-1">
            <div class="col-12">
              <div class="row no-gutters">
                <div class="col-12 sidebar-item" @click="isShowCategories = !isShowCategories" :class="{'sidebar-item-active': isShowCategories}">Категорії</div>
                <div class="col-12 item-dropdown" v-if="isShowCategories">
                  <div v-for="category in categories" class="row align-items-center">
                    <div class="col">
                      <div class="row category" @click="category.isOpened = !category.isOpened">
                        <div class="col-2">
                          <img src="../assets/img/test/category.png" alt="">
                        </div>
                        <div class="col">{{category.name}}}</div>
                      </div>
                      <div class="row">
                        <div class="col-12 p-2" v-if="category.isOpened">
                          <div class="row no-gutters">
                            <a v-for="subcategory in category.subcategories" href="#" class="col-12 subcategory">{{subcategory.name}} 1</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row no-gutters sidebar-item">Про нас</div>
              <div class="row no-gutters sidebar-item">Оплата та доставка</div>
              <div class="row no-gutters sidebar-item">Правила розміщення товарів</div>
              <div class="row no-gutters p-2 search-field">
                <label for="search" class="col-12 d-md-none">Пошук по сайту</label>
                <input id="search" class="col-10" type="search">
                <span class="col-2 text-center">
                <img src="../assets/img/nav-bar/search.png" alt="">
              </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
  let carIte1 = {
    id: 0,
    name: 'Апельсин',
    imageSrc: 'https://wp.miray.com.ua/site/miray/files/wallpapers/wallpaper-4187.jpg',
    priceForMeasurementUnit: 15.60,
    sellerName: 'marius961',
    measurementUnit: 'кг.',
    quantity: 3,
  };
  let carIte2 = {
    id: 1,
    imageSrc: 'https://irecommend.ru/sites/default/files/product-images/42233/zwr0obbJCnszZYssy91zA.jpg',
    name: 'Мука',
    priceForMeasurementUnit: 20,
    quantity: 2,
    measurementUnit: 'кг.',
  };
  let carIte3 = {
    id: 2,
    imageSrc: 'https://i.biz-gid.com/img/products/800/216796.png',
    name: 'Хліб',
    priceForMeasurementUnit: 8.50,
    quantity: 2,
    measurementUnit: 'шт.',
  };
  let carIte4 = {
    id: 2,
    imageSrc: 'https://img2.zakaz.ua/src.1470729330.ad72436478c_2016-08-09_Aleksey/src.1470729330.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.1350nowm.jpg.1350x.jpg',
    name: 'Моршинська (сильногазована)',
    priceForMeasurementUnit: 12.50,
    quantity: 1,
    measurementUnit: 'л.',
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
        categories: [category, category2],
        isShowSideBar: false,
        isShowCategories: false,
      }
    },
    methods: {
      calculateCartSum() {
        let sum = 0;
        this.cartItems.map(item => {
          sum += item.priceForMeasurementUnit * item.quantity
        });
        return sum;
      },
      closeSideBar() {
        this.isShowSideBar = false;
        setTimeout(() => {
          this.isShowCategories = false;
          this.categories.map(category => {
            category.isOpened = false;
          }, 300)
        })
      },
      toggleSidebarVisibility() {
        if (this.isShowSideBar) {
          this.closeSideBar();
        } else {
          this.isShowSideBar = true;
        }
      }
    }
  }
</script>

<style scoped>
  @import "../assets/css/nav-bar.css";



</style>
