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
                    <router-link to="/add-product" class="col-12" href="#">Додати товар</router-link>
                    <router-link to="/add-category" class="col-12" href="#">Додати категорію/підкатегорію</router-link>
                    <hr class="w-100 m-1">
                    <a class="col-12" href="#">Вийти</a>
                  </div>
                </div>
              </div>
              <div class="col-auto">
                <div class="row align-items-center nav-dropdown">
                  <div class="cart-items-count" v-if="cart.length > 0">{{cart.length}}</div>
                  <div class="col-2">
                    <img src="../assets/img/nav-bar/cart-icon.png" alt="">
                  </div>
                </div>
                <div class="nav-dropdown-menu cart-dropdown-menu">
                  <div class="row m-0">
                    <div class="col-12">
                      <div class="row nav-cart-box p-1">
                        <div class="col">
                          <transition-group name="list">
                            <div v-for="cartItem in cart" :key="'cartItem' + cartItem.id" class="row nav-cart-item align-items-center p-3 mt-1 mb-1">
                              <div class="col-3 col-sm-auto text-center">
                                <img :src="cartItem.image" alt="">
                              </div>
                              <div class="col-7 col-sm align-self-center nav-cart-item-text">{{cartItem.name}}</div>
                              <div class="col-2 col-sm-auto align-self-center align-self-sm-start remove-cart-item-btn" @click="deleteItemFromCartById(cartItem.id)">
                                <img src="../assets/img/delete.png" alt="">
                              </div>
                              <hr class="w-100">
                              <div class="col">
                                <div class="row">
                                  <div class="col-6 text-center">{{cartItem.quantity}}{{cartItem.measurementUnit.name}}</div>
                                  <div class="col-6 text-center">{{cartItem.priceForMeasurementUnit * cartItem.quantity}} грн</div>
                                </div>
                              </div>
                            </div>
                          </transition-group>
                          <transition name="list">
                            <div v-if="isCartEmpty" class="row empty-cart-message">
                              <div class="col-12 text-center">
                                <img src="../assets/img/empty-box.png" alt="">
                              </div>
                              <h5 class="col-12 text-center">Схоже, кошик пустий</h5>
                            </div>
                          </transition>
                        </div>
                      </div>
                    </div>
                    <hr>
                    <div v-if="calculateCartSum > 0" class="col-12 nav-cart-total-price p-2 mt-2 text-center">Сума: {{calculateCartSum}}грн</div>
                    <router-link tag="button" to="/cart" v-if="calculateCartSum > 0" class="col-12 nav-go-to-cart-btn p-2 mt-2 text-center">Перейти у кошик</router-link>
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
                <router-link to="/" class="col-2 pb-2">
                  <img src="../assets/img/nav-bar/home.png" alt="">
                </router-link>
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
                        <div class="col">{{category.name}}</div>
                      </div>
                      <div class="row">
                        <div class="col-12 p-2" v-show="category.isOpened">
                          <div class="row no-gutters">
                            <router-link :to="'/products/' + subcategory.id" v-for="subcategory in category.subcategories" :key="subcategory.id" href="#" class="col-12 subcategory">{{subcategory.name}} 1</router-link>
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
  import {mapGetters} from 'vuex'
  import {mapActions} from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        getCategoriesMap: 'getCategoriesMap',
        cart: 'getAllCartItems'
      }),
      isCartEmpty() {
        return !this.cart.length > 0
      },
      calculateCartSum() {
        let sum = 0;
        this.cart.map(item => {
          sum += item.priceForMeasurementUnit * item.quantity
        });
        return sum;
      }
    },
    data() {
      return {
        categories: [],
        cartSum: 0,
        isShowSideBar: false,
        isShowCategories: false,
      }
    },
    methods: {
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
      },
      ...mapActions([
        'fetchCart',
        'deleteItemFromCartById',
        'fetchCategories'
      ])
    },
    created() {
      this.fetchCart();
      this.fetchCategories().then(() => {
        this.categories = this.getCategoriesMap.map((category) => {
          return {...category, isOpened: false};
        })
      })
    }
  }
</script>

<style scoped>
  @import "../assets/css/nav-bar.css";


  .list-enter-active, .list-leave-active {
    transition: .3s;
  }
  .list-enter, .list-leave-to {
    opacity: 0;
    transform: translateX(-100%);
  }

</style>
