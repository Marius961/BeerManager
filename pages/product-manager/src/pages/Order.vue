<template>
  <main class="container mt-4 mb-4">
    <div class="row align-items-center">
      <div class="col-12">
        <h4>Оберіть адресу</h4>
        <hr class="w-100">
      </div>
    </div>
    <div class="row" style="flex-wrap: nowrap; overflow-y: auto">
      <div class="col-10 col-sm-8 col-md-6 col-lg-4 address-card" v-for="address in addressList" :key="'address' + address.id">
        <div class="row h-100 card-body">
          <div class="col-12">
            <card-row title="П.І.Б. отримувача:">{{address.recipientFullName}}</card-row>
            <card-row title="Мобільний телефон отримувача:">{{address.recipientMobileNumber}}</card-row>
            <card-row title="Область:">{{address.region}}</card-row>
            <card-row title="Місто:">{{address.city}}</card-row>
            <card-row title="Вулиця:">{{address.street}}</card-row>
            <card-row title="Номер будинку:">{{address.buildingNumber}}</card-row>
            <card-row title="Квартира/офіс:">{{address.apartmentNumber}}</card-row>
            <card-row title="Поштовий індекс:">{{address.zipCode}}</card-row>
          </div>
          <div class="col-12 select-address-btn align-self-end">
            <input type="radio" :id="'address' + address.id" v-model="selectedAddressId" :value="address.id">
            <label class="wrapper" :for="'address' + address.id">Вибрати</label>
          </div>
        </div>
      </div>
    </div>


    <div class="row align-items-start mt-5">
      <div class="col-12">
        <div class="row">
          <div class="col-12 col-sm-auto text-center text-sm-left">
            <h4>Товари для замовлення</h4>
          </div>
          <div class="col-12 col-sm text-center text-sm-right">Продавець <span class="font-weight-bold">продавець</span></div>
          <hr class="w-100">
        </div>
      </div>
      <div class="col-12">
        <div class="row p-3 d-none d-md-flex">
          <div class="col-6 offset-2 text-center font-weight-bold">Назва позиції</div>
          <div class="col-2 text-center font-weight-bold">Кількість</div>
          <div class="col-2 text-center font-weight-bold">Кінцева ціна</div>
        </div>
        <div class="row">
          <div class="col">
            <div v-for="product in items" class="row align-items-center product-row">
              <div class="col-2 text-center">
                <img :src="product.image" alt="">
              </div>
              <div class="col-10 col-md-6 text-center">{{product.name}}</div>
              <hr class="w-100 d-md-none">
              <div class="col-6 col-md-2">
                <div class="row justify-content-center align-items-center">
                  <div class="col-12 col-sm-8 col-md-auto text-center">
                    <div class="row no-gutters justify-content-center font-weight-light fs-4">
                      <div class="col-auto text-center text-sm-left">{{product.quantity}}</div>
                      <div class="col-auto text-center text-sm-left">{{product.measurementUnit.name}}</div>
                    </div>
                  </div>
                  <label class="col-12 col-sm-4 d-md-none pt-2 text-center order-sm-first font-weight-bold">кількість</label>
                </div>
              </div>
              <div class="col-6 col-md-2">
                <div class="row justify-content-center align-items-center">
                  <div class="col-12 col-sm-auto text-center fs-4 text-sm-left font-weight-light">{{product.quantity * product.priceForMeasurementUnit}} грн</div>
                  <label class="col-12 col-sm-auto d-md-none pt-2 text-center order-sm-first font-weight-bold">ціна</label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mt-5 align-items-center">
      <div class="col-12 col-sm-auto text-center text-sm-left">
        <h4>Товари для замовлення</h4>
      </div>
      <div class="col-12 col-sm text-center text-sm-right">Продавець <span class="font-weight-bold">продавець</span></div>
      <hr class="w-100">
      <label class="col-12">
        <textarea v-bind="comment" class="input-2 p-3" rows="3"></textarea>
      </label>
    </div>
    <div class="col-12">
      <hr class="w-100 pt-5 pb-2">
      <div class="row align-items-center">
        <div class="col-12 col-sm-7 col-lg-9 fs-4 font-weight-light p-1 text-center text-sm-left">Кінцева сума до сплати:
          <span class="font-weight-bold">{{totalPrice}}грн</span>
        </div>
        <button  @click="submitOrder()" :disabled="!isOrderValid()" class="col-12 col-sm-5 col-lg-3 order-submit-btn" type="submit">Оформти замовлення</button>
      </div>
    </div>
  </main>
</template>

<script>

  import AddressCardRow from '../components/AddressCardRow'
  import {mapGetters} from 'vuex'
  import {mapActions} from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        isItemsReady: "isItemsReadyToOrder",
        addressList: "getAddressList",
        items: "getItemsForOrder",
      }),
      totalPrice() {
        let price = 0;
        this.items.map((item) => {
          price+= item.quantity * item.priceForMeasurementUnit
        });
        return price;
      }
    },
    data() {
      return {
        selectedAddressId: undefined,
        comment: ""
      }
    },
    components: {
      "card-row": AddressCardRow
    },
    methods: {
      ...mapActions({
        fetchAddressList: "fetchAddressList",
        postOrder: "postOrder"
      }),
      confirmLeave() {
        return window.confirm("Ви впевнені що хочете залишити сторінку? Дані будуть втрачені!")
      },
      submitOrder() {
        if (this.isOrderValid()) {
          let orderItems = this.items.map(item => {
            return {
              id: item.id,
              quantity: item.quantity,
              price: item.quantity * item.priceForMeasurementUnit
            }
          });
          this.postOrder({
            addressId: this.selectedAddressId,
            orderItems,
            comment: this.comment
          })
        }
      },
      isOrderValid() {
        if (this.addressList.length <= 0) {
          return false;
        }
        else if (typeof this.selectedAddressId === 'undefined') {
          return false;
        }
        else if (this.items.length <= 0) {
          return false;
        }
        return true;
      }
    },
    created() {
      if (!this.isItemsReady) {
        window.onbeforeunload = () => {return false};
        this.fetchAddressList();
      } else {
        this.$router.push("/cart")
      }
    },
    beforeRouteLeave(to, from, next) {
      if (this.confirmLeave()) {
        next();
      }
    }
  }
</script>

<style scoped>
  @import "../assets/css/order.css";
</style>
