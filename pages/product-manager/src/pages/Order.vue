<template>
  <main class="container mt-4 mb-4">
    <div class="row" style="flex-wrap: nowrap; overflow-y: auto">
      <div class="col-10 col-sm-8 col-md-6 col-lg-4 address-card" v-for="address in addressList" :key="'address' + address.id">
        <div class="row h-100 card-body">
          <div class="col-12">
            <div class="row">
              <div class="col-12 font-weight-light">П.І.Б. отримувача:</div>
              <div class="col-12 font-weight-bold">{{address.recipientFullName}}</div>
            </div>
            <div class="row">
              <div class="col-12 font-weight-light">Мобільний телефон отримувача:</div>
              <div class="col-12 font-weight-bold">{{address.recipientMobileNumber}}</div>
            </div>
            <div class="row">
              <div class="col-auto font-weight-light">Область:</div>
              <div class="col font-weight-bold">{{address.region}}</div>
            </div>
            <div class="row">
              <div class="col-auto font-weight-light">Місто:</div>
              <div class="col font-weight-bold">{{address.city}}</div>
            </div>
            <div class="row">
              <div class="col-auto font-weight-light">Вулиця:</div>
              <div class="col font-weight-bold">{{address.street}}</div>
            </div>
            <div class="row">
              <div class="col-auto font-weight-light">Номер будинку:</div>
              <div class="col font-weight-bold">{{address.buildingNumber}}</div>
            </div>
            <div class="row">
              <div class="col-auto font-weight-light">Квартира/офіс:</div>
              <div class="col font-weight-bold">{{address.apartmentNumber}}</div>
            </div>
            <div class="row">
              <div class="col-auto font-weight-light">Поштовий індекс:</div>
              <div class="col font-weight-bold">{{address.zipCode}}</div>
            </div>
          </div>
          <div class="col-12 select-address-btn align-self-end">
            <input type="radio" :id="'address' + address.id" v-model="selectedAddressId" :value="address.id">
            <label class="wrapper" :for="'address' + address.id">Вибрати</label>
          </div>
        </div>
      </div>
    </div>


    <!--<div class="row box align-items-start p-3">-->
      <!--<div class="col-12">-->
        <!--<div class="row">-->
          <!--<div class="col-12 fs-4 p-3">Оберіть адресу отримувача:</div>-->
          <!--<div class="col-12 p-3 ">-->
            <!--<div class="row address-container" style="flex-wrap: nowrap">-->
              <!--<div class="col-10 col-sm-7 col-md-5 col-lg-4 col-xl-3 p-4 address-card selected-address">-->
                <!--<div class="row">-->

                <!--</div>-->
              <!--</div>-->
              <!--<div class="col-10 col-sm-7 col-md-5 col-lg-4 col-xl-3 address-card text-center add-address-btn" id="addAddressBtn">-->
                <!--<div class="row justify-content-center h-100 align-items-center">-->
                  <!--<div class="col-12">-->
                    <!--<div class="row">-->
                      <!--<div class="col-12"><img src="img/plus.png"></div>-->
                      <!--<div class="col-12 mt-2 font-weight-bold">Додати адресу</div>-->
                    <!--</div>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
      <!--<div class="col-12">-->
        <!--<div class="w-100 pt-5 pb-2"><hr></div>-->
        <!--<div class="row">-->
          <!--<div class="col">Продавець: <span class="font-weight-bold">ivam75423</span> </div>-->
        <!--</div>-->
        <!--<div class="row p-3 d-none d-md-flex border-box-1">-->
          <!--<div class="col-6 offset-2 text-center font-weight-bold">Назва позиції</div>-->
          <!--<div class="col-2 text-center font-weight-bold">Кількість</div>-->
          <!--<div class="col-2 text-center font-weight-bold">Кінцева ціна</div>-->
        <!--</div>-->
        <!--<div class="row p-2">-->
          <!--<div class="col-12">-->
            <!--<div class="row p-3 align-items-center">-->
              <!--<div class="col-2 text-center">-->
                <!--<img src="img/test/product.png" width="64" height="64">-->
              <!--</div>-->
              <!--<div class="col-10 col-md-6">Повна назва товару з переносом на наступний рядок через переповнення блоку</div>-->
              <!--<hr class="w-100 d-md-none">-->
              <!--<div class="col-6 col-md-2">-->
                <!--<div class="row justify-content-center align-items-center">-->
                  <!--<div class="col-12 col-sm-8 col-md-auto text-center">-->
                    <!--<div class="row no-gutters justify-content-center font-weight-light fs-4">-->
                      <!--<div class="col-auto text-center text-sm-left">13</div>-->
                      <!--<div class="col-auto text-center text-sm-left">кг</div>-->
                    <!--</div>-->
                  <!--</div>-->
                  <!--<label class="col-12 col-sm-4 d-md-none pt-2 text-center order-sm-first font-weight-bold">кількість</label>-->
                <!--</div>-->
              <!--</div>-->
              <!--<div class="col-6 col-md-2">-->
                <!--<div class="row justify-content-center align-items-center">-->
                  <!--<div class="col-12 col-sm-auto text-center fs-4 text-sm-left font-weight-light">19548грн</div>-->
                  <!--<label class="col-12 col-sm-auto d-md-none pt-2 text-center order-sm-first font-weight-bold">ціна</label>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
        <!--</div>-->
        <!--<div class="row">-->
          <!--<div class="col-12 col-sm-8">-->
            <!--<label for="commentArea">Коментар до замовлення:</label>-->
            <!--<textarea class="input-2 p-3" id="commentArea" rows="3"></textarea>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
      <!--<div class="col-12">-->
        <!--<hr class="w-100 pt-5 pb-2">-->
        <!--<div class="row align-items-center">-->
          <!--<div class="col-12 col-sm-7 col-lg-9 fs-4 font-weight-light p-1 text-center text-sm-left">Кінцева сума до сплати:-->
            <!--<span class="font-weight-bold">9999999.99грн</span>-->
          <!--</div>-->
          <!--<button class="col-12 col-sm-5 col-lg-3 submit-btm p-3 p-sm-1 font-weight-bold" type="submit">Оформти замовлення</button>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
  </main>
</template>

<script>
    export default {
        data() {
          return {
            selectedAddressId: "",
            addressList: [
              {
                id: 0,
                recipientFullName: "Іван Васильович",
                recipientMobileNumber: "+381848754948",
                region: "Львівська",
                city: "Львів",
                street: "Степана Бандери",
                buildingNumber: "12 a",
                apartmentNumber: "2",
                zipCode: "78008"
              },
              {
                id: 1,
                recipientFullName: "Олег Васильович",
                recipientMobileNumber: "+381848154948",
                region: "Ки]вська обл. Бориспіль qwd qwdqwdwqd",
                city: "Київ",
                street: "Хрещатик",
                buildingNumber: "28Б",
                apartmentNumber: "46",
                zipCode: "79008"
              },
              {
                id: 3,
                recipientFullName: "Олег Васильович",
                recipientMobileNumber: "+381848154948",
                region: "Ки]вська обл. Бориспіль",
                city: "Київ",
                street: "Хрещатик",
                buildingNumber: "28Б",
                apartmentNumber: "46",
                zipCode: "79008"
              }
            ]
          }
        }
    }
</script>

<style scoped>
  @import "../assets/css/order.css";
</style>
