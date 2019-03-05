<template>
    <div class="container">
        <div class="row">
            <button class="col-12 filters-btn d-md-none" @click="isShowFilters = !isShowFilters">
                <i class="fas fa-filter"></i> Фільтри
            </button>
            <div class="col-12 col-md-3 filters-segment" :class="{'hide-filters': isShowFilters}">
                <div class="row">
                    <div class="col filter-group">
                        <div class="row">
                            <div class="col-12 p-0 filter-header">Фыльтр</div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <label class="checkbox-container">Фільтр
                                    <input type="checkbox" checked="checked">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                            <div class="col-12">
                                <label class="checkbox-container">Фільтр
                                    <input type="checkbox" checked="checked">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                            <div class="col-12">
                                <label class="checkbox-container">Фільтр
                                    <input type="checkbox" checked="checked">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-9">
                <div class="row">
                    <product-card
                            v-for="product in products" :key="product.id"
                            class="col-12 col-sm-6 col-lg-4"
                            :id="product.id"
                            :name="product.name"
                            :imgSrc="product.imgSrc"
                            :priceForMeasurementUnit="product.priceForMeasurementUnit"
                            :measurementUnit="product.measurementUnit"
                            :sellerName="product.sellerName"
                            :isInStock="product.isInStock"
                            :isInFavorites="product.isInFavorites"
                    >
                    </product-card>
                </div>
                <div class="row justify-content-center   pagination">
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import ProductCard from "../../components/ProductCard";

    let product1 = {
        id: 0,
        name: 'Молоко',
        imgSrc: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKtngGfEjZwPMFDFGOmxTeyrrObo-GE0Y44GSxKtdtINAZaL_P',
        priceForMeasurementUnit: 22.90,
        measurementUnit: {
            id: 13,
            name: 'кг.',
            fullName: ''
        },
        sellerName: 'marius961',
        isInStock: true,
        isInFavorites: false

    };

    export default {
        data() {
            return {
                isShowFilters: false,
                products: [product1,product1,product1,product1,product1,product1,product1,product1,product1,product1,product1,product1,]
            }
        },
        components: {
            "product-card": ProductCard
        },
    }
</script>

<style scoped>
    @import "../../assets/css/productByCategory.css";
</style>
