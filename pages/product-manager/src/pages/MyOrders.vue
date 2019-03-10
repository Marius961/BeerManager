<template>
    <div class="container">
        <div class="row">
            <h1 class="col-12 mt-2">Мої замовлення</h1>
            <hr class="w-100">
            <div class="col-12 order-sort">
                <div class="row align-items-center justify-content-end">
                    <a href="#" class="col-6 col-sm-auto">
                        <div class="row no-gutters">
                            <i class="fas fa-box col-12 col-sm-auto"></i>
                            <span class="col-12 col-sm-auto">Активні замовлення</span>
                        </div>
                    </a>
                    <a href="#" class="col-6 col-sm-auto">
                        <div class="row no-gutters">
                            <i class="fas fa-history col-12 col-sm-auto"></i>
                            <span class="col-12 col-sm-auto">Істоія замовлень</span>
                        </div>
                    </a>
                </div>
            </div>
            <hr class="w-100">
        </div>
        <div class="row">
            <order-row class="col-12"></order-row>
        </div>
    </div>
</template>

<script>

    import OrderRow from '../components/OrderRow'

    export default {
        components: {
            "order-row": OrderRow
        }
    }
</script>

<style scoped>
    @import "../assets/css/myOrders.css";
</style>
