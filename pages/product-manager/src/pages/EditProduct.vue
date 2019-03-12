<template>
    <div class="container">
        <div class="row mt-2">
            <div class="col-12 col-sm-6 h1">{{name}}</div>
            <div class="col-12 f-form-group-2">
                <label for="image">Завантажити нове фото товару</label>
                <input type="file" id="image">
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="name">Назва товару</label>
                <input type="text" v-model="name" id="name">
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="priceForMeasurementUnit">Ціна за одиницю</label>
                <input type="text" v-model="priceForMeasurementUnit" id="priceForMeasurementUnit">
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="measurementUnit">Одиниця вимірювання</label>
                <input disabled type="text" v-model="measurementUnit" id="measurementUnit">
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="category">Категорія</label>
                <input disabled type="text" v-model="category" id="category">
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="subcategory">Підкатегорія</label>
                <input disabled type="text" v-model="subcategory" id="subcategory">
            </div>
            <div class="col-12 f-form-group-2">
                <label for="description">Опис</label>
                <textarea rows="4" v-model="description" id="description"></textarea>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                name: 'Молоко',
                priceForMeasurementUnit: '32',
                measurementUnit: 'кг.',
                category: 'Молоко та яйця',
                subcategory: 'Молоко',
                image: '',
                imageSrc: '',
                description: 'Опис продукту'
            }
        },
        created() {
            alert(this.$route.params.id);
        }
    }
</script>

<style scoped>
    @import "../assets/css/forms.css";
</style>
