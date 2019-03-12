<template>
    <div class="container mt-3">
        <div class="row product-item">
            <div class="col-12 col-md-4 col-lg-3 text-center">
                <span class="on-sale">продається</span>
                <img src="https://cdn.shopify.com/s/files/1/0206/9470/products/southcoast-milk-1l_1024x1024.jpg?v=1494139427" alt="">
            </div>
            <div class="col">
                <div class="row align-items-center">
                    <div class="col h3">Молоко</div>
                    <div class="col-auto action-btn">
                        <i class="fas fa-trash-alt"></i>
                    </div>
                    <router-link tag="div" to="/edit-product/1" class="col-auto action-btn">
                        <i class="fas fa-edit"></i>
                    </router-link>
                </div>
                <hr class="w-100 m-2 ml-3 mr-3">
            </div>
            <transition name="scale">
                <div class="col-12" v-if="isShowDetails">
                    <div class="row">
                        <div class="col-12 col-sm-4">
                            <div class="row no-gutters h-100 info-box text-center align-items-center">
                                <div class="col-12">3212 &#8372;</div>
                                <label class="col-12">за 1 кг</label>
                            </div>
                        </div>
                        <div class="col-12 col-sm-4">
                            <div class="row no-gutters h-100 info-box text-center align-items-center">
                                <div class="col-12">32123</div>
                                <label class="col-12">л. продано</label>
                            </div>
                        </div>
                        <div class="col-12 col-sm-4">
                            <div class="row no-gutters h-100 info-box text-center align-items-center">
                                <div class="col-12">1323</div>
                                <label class="col-12">замовлень</label>
                            </div>
                        </div>
                    </div>
                </div>
            </transition>
            <div class="col-12">
                <div class="row justify-content-end">
                    <a href="#" class="col-auto show-link" @click.prevent="isShowDetails = !isShowDetails">
                        <i class="fas fa-caret-down"></i>
                        Показати деталі
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                isShowDetails: false
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/myProducts.css";
    @import "../assets/css/transitions.css";
</style>
