<template>
    <div class="container">
        <div class="row justify-content-between align-items-center mt-2">
            <div class="col-auto h1">Product name</div>
            <div class="col-auto sku-number">№: 3123</div>
        </div>
        <hr class="w-100 m-1">
        <div class="row mt-4">
            <div class="col-12 col-md-5 col-lg-4 product-img text-center">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKtngGfEjZwPMFDFGOmxTeyrrObo-GE0Y44GSxKtdtINAZaL_P" alt="">
            </div>
            <div class="col-12 col-md-7 col-lg-8 pl-1 pr-1">
                <div class="row">
                    <div class="col-12 segment">
                        <div class="row align-items-center justify-content-between">
                            <div class="col-12 col-sm-auto text-center text-sm-left">32 грн/кг</div>
                            <div class="col-12 col-sm-auto">
                                <div class="row justify-content-center no-gutters">
                                    <label for="quantity" class="d-none"></label>
                                    <input class="col-auto quantity-input" value="1" type="number" id="quantity">
                                    <div class="col-auto to-cart-btn">
                                        <i class="fas fa-cart-plus"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 segment">Продавець: <span class="font-weight-light">Іван</span></div>
                    <div class="col-12 segment">
                        <div class="row">
                            <div class="col-12">Опис:</div>
                            <p class="col-12 font-weight-normal">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                A architecto eveniet officiis provident recusandae? Consequatur
                                dolorum ipsa laboriosam non nostrum, perspiciatis possimus quos sequi sint.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        created() {
            console.log(this.$route.params.id)
        }
    }
</script>

<style scoped>
    @import "../../assets/css/productPage.css";
</style>
