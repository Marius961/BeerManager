<template>
    <div class="order-row">
        <div class="row p-3">
            <div class="col-6">
                <div class="row justify-content-start">
                    <div class="col-auto order-info">
                        Замовлення № <span class="font-weight-bold">123123</span>
                    </div>
                    <div class="col-auto order-info">
                        Замовник <span class="font-weight-bold">Іван Васильович</span>
                    </div>
                    <div class="col-auto order-info">
                        Продавець <span class="font-weight-bold">Ви</span>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="row justify-content-end">
                    <div class="col-auto order-info">
                        Дата замовлення: <span class="font-weight-bold">32.32.2131  22:13</span>
                    </div>
                </div>
            </div>
        </div>
        <hr class="w-100 m-1 mt-0">
        <div class="row align-items-start justify-content-between pl-3 pr-3">
            <transition name="scale">
                <div class="col-12 order-items-list" v-if="isShowOrderItems">
                    <div class="row list-header">
                        <div class="col-6 font-weight-bold text-center">Назва</div>
                        <div class="col-4 font-weight-bold text-center">Кількість</div>
                        <div class="col-2 font-weight-bold text-center">Ціна</div>
                    </div>
                    <div class="row order-list-item">
                        <div class="col-6 text-center">Молоко домашнє</div>
                        <div class="col-4 text-center">32літри</div>
                        <div class="col-2 text-center">500грн</div>
                    </div>
                </div>
            </transition>
            <div class="w-100 mt-2"></div>
            <div class="col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 products-count p-btn order-products-btn align-self-center" @click="isShowOrderItems = !isShowOrderItems">
                <div class="row no-gutters align-items-center justify-content-center">
                    <i class="fas fa-box-open col-auto"></i>
                    <span class="col-auto">999999 товарів</span>
                </div>
                <div class="b-overlay"> Показати</div>
            </div>
            <div class="col-12 col-sm-6 col-lg-3 col-xl-2 mt-4 mt-sm-0">
                <div class="row">
                    <div class="col-12 h4 text-center">Сума:</div>
                    <div class="col-12 align-self-end text-center">3232</div>
                </div>
            </div>
            <div class="col-12 col-sm-12 col-lg-5 col-xl-7 mt-4 mt-lg-0">
                <div class="row">
                    <div class="col-12 h4 text-center">Статус:</div>
                    <div class="col-12">
                        <div class="row align-items-center">
                            <div class="col-6 col-sm-4 text-sm-right">
                                <i class="far fa-calendar"></i> 21.03.2019
                                <div class="w-100"></div>
                                <i class="far fa-clock"></i> 22:32
                            </div>
                            <div class="col-6 col-sm-8">Розміщено</div>
                        </div>
                    </div>
                    <transition name="scale">
                        <div class="col-12" v-if="isShowStatusHistory">
                            <hr class="w-100">
                            <div class="row">
                                <div class="col-4 text-right">
                                    <i class="far fa-calendar"></i> 21.03.2019
                                    <div class="w-100"></div>
                                    <i class="far fa-clock"></i> 22:32
                                </div>
                                <div class="col-8">Замовлення розміщено</div>
                            </div>
                            <div class="row">
                                <div class="col-4 text-right">
                                    <i class="far fa-calendar"></i> 21.03.2019
                                    <div class="w-100"></div>
                                    <i class="far fa-clock"></i> 22:32
                                </div>
                                <div class="col-8">
                                    Замовлення розміщено
                                </div>
                            </div>
                        </div>
                    </transition>
                </div>
                <div class="row justify-content-center justify-content-lg-end" @click="isShowStatusHistory = !isShowStatusHistory">
                    <a href="#" class="col-auto show-link">
                        <i class="fas fa-caret-down"></i>
                        Показати всі
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <transition name="scale">
                <div class="col-12 comment-segment" v-if="isShowComment">
                    <div class="row">
                        <h4 class="col-12">Коментар:</h4>
                        <p class="col-12">Коментар до замовлення який показується при бажанні</p>
                    </div>
                </div>
            </transition>
            <transition name="scale">
                <div class="col-12" v-if="isShowAddress">
                    <card-row title="П.І.Б. отримувача:">Олег Васильович</card-row>
                    <card-row title="Мобільний телефон отримувача:">+381848154948</card-row>
                    <card-row title="Область:">Ки]вська обл. Бориспіль</card-row>
                    <card-row title="Місто:">Київ</card-row>
                    <card-row title="Вулиця:">Хрещатик</card-row>
                    <card-row title="Номер будинку:">28Б</card-row>
                    <card-row title="Квартира/офіс:">46</card-row>
                    <card-row title="Поштовий індекс:">79008</card-row>
                </div>
            </transition>
        </div>
        <hr class="w-100">
        <div class="row justify-content-end ">
            <a href="#" class="col-6 col-sm-auto show-link" @click.prevent="isShowComment = !isShowComment">
                <i class="fas fa-comment"></i>
                Показати коментар
            </a>
            <a href="#" class="col-6 col-sm-auto show-link" @click.prevent="isShowAddress = !isShowAddress">
                <i class="fas fa-map-marker-alt"></i>
                Показати адресу та дані отримувача
            </a>
        </div>
        <slot></slot>
        <hr class="w-100 m-1">
    </div>
</template>

<script>

    import CardRow from '../components/AddressCardRow'

    export default {
        data() {
            return {
                isShowComment: false,
                isShowAddress: false,
                isShowOrderItems: false,
                isShowStatusHistory: false
            }
        },
        components: {
            "card-row": CardRow
        },
        methods: {
        }
    }
</script>

<style scoped>
    @import "../assets/css/orderRow.css";
    @import "../assets/css/transitions.css";
</style>
