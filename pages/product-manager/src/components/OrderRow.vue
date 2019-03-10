<template>
    <div class="order-row">
        <div class="row justify-content-between">
            <div class="col-12 col-sm-auto order-info">
                Замовлення № <span class="font-weight-bold">123123</span>
            </div>
            <div class="col-12 col-sm-auto order-info">
                Дата замовлення: <span class="font-weight-bold">32.32.2131</span>
            </div>
        </div>
        <hr class="w-100">
        <div class="row align-items-center">
            <div class="col-4 col-sm-auto text-center order-1">
                <img src="../assets/img/box.png" alt="">
            </div>
            <div class="col-4 col-sm-2 order-products-count order-2">32</div>
            <hr class="w-100 d-sm-none order-4">
            <div class="col-12 col-sm order-4 order-sm-3">
                <div class="row">
                    <div class="col-12 status-header text-center">Статус:</div>
                    <div class="col-12 text-center">Очікує підтвердження</div>
                </div>
            </div>
            <div class="col-4 col-sm-2 text-center order-3 order-sm-4">
                <a href="#">деталі</a>
            </div>
        </div>
        <slot></slot>
        <hr class="w-100 m-1">
    </div>
</template>

<script>
    export default {
    }
</script>

<style scoped>
    @import "../assets/css/orderRow.css";
</style>
