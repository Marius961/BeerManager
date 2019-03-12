<template>
    <div class="container">
        <div class="row align-items-center mt-5">
            <div class="h3 col-12">Додати категорію</div>
            <hr class="w-100 m-1">
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="categoryImg">Іконка категорії</label>
                <input id="categoryImg" @change="inputChange"  type="file">
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="categoryName">Назва категорії</label>
                <input id="categoryName" v-model="category.name" type="text">
            </div>
            <div class="col-12">
                <div class="row justify-content-sm-end pl-1 pr-1">
                    <button type="submit" class="col-12 text-center p-btn p-btn-primary p-2">Додати категорію</button>
                </div>
            </div>
        </div>
        <div class="row align-items-center mt-5">
            <div class="h3 col-12">Додати підкатегорію</div>
            <hr class="w-100 m-1">
            <div class="col-12 col-sm-6 f-form-group-2">
                <label v-model="selectedCategoryId" for="categories">Іконка категорії</label>
                <select id="categories">
                    <option value="" disabled> -Виберіть категорію</option>
                    <option value="c1">Категорія 1</option>
                    <option value="c2">Категорія 2</option>
                    <option value="c3">Категорія 3</option>
                </select>
            </div>
            <div class="col-12 col-sm-6 f-form-group-2">
                <label for="subcategoryName">Назва категорії</label>
                <input id="subcategoryName" v-model="subcategory.name" type="text">
            </div>
            <div class="col-12">
                <div class="row justify-content-sm-end pl-1 pr-1">
                    <button type="submit" class="col-12 text-center p-btn p-btn-primary p-2">Додати категорію</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                category: {
                    image: '',
                    name: ''
                },
                subcategory: {
                    name: ''
                },
                selectedCategoryId: ''
            }
        },
        methods: {
            inputChange(e) {
                this.image = e.target.files[0];
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/forms.css";
</style>
