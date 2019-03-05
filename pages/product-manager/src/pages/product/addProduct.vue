<template>
    <div class="container pt-3 pb-3">
        <div class="row">
            <div class="col-12 h1">Додати товар</div>
            <hr class="w-100">
            <label class="col-12 col-md-6 col-lg-4 photo-input">
                <div class="row justify-content-center">
                    <img v-if="isPhotoSelected" class="col-auto" :src="imageSrc" alt="">
                    <i v-if="!isPhotoSelected" class="col-auto fas fa-image"></i>
                    <input type="file" @change="onChange($event)">
                    <span v-if="!isPhotoSelected" class="col-12">Виберіть фото</span>
                </div>
            </label>
            <div class="col-12 col-md-6 col-lg-8">
                <div class="row">
                    <div class="col-12 f-form-group">
                        <label for="nameInput">Назва товару</label>
                        <input type="text" v-model="name" id="nameInput">
                    </div>
                    <div class="col-12 col-sm-6 f-form-group">
                        <div class="row no-gutters">
                            <label class="col-12" for="price">Ціна за одиницю</label>
                            <div class="col-12">
                                <div class="row no-gutters align-items-center">
                                    <input class="col" v-model="priceForMeasurementUnit" type="number" id="price">
                                    <div class="col-auto pl-2">грн</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 f-form-group">
                        <div class="row no-gutters">
                            <label class="col-12 pl-4" for="measurementUnit">Одиниці</label>
                            <div class="col-12">
                                <div class="row no-gutters align-items-center">
                                    <div class="col-auto pr-2">за</div>
                                    <select v-model="selectedMeasurementUnit" class="col-auto" id="measurementUnit">
                                        <option value="" disabled selected></option>
                                        <option value="">мл</option>
                                        <option value="">л</option>
                                        <option value="">гр</option>
                                        <option value="">кг</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12 col-sm-6 f-form-group p-4">
                <div class="row">
                    <label class="col-12" for="categories">Виберіть категорю</label>
                    <select v-model="selectedCategory" class="col-12" id="categories">
                        <option value="c1" disabled selected></option>
                        <option value="c2">Категорія !</option>
                        <option value="c3">Категорія 2</option>
                        <option value="c4">Категорія 3</option>
                        <option value="c5">Категорія 4</option>
                    </select>
                </div>
            </div>
            <div class="w-100"></div>
            <div class="col-12 col-sm-6 f-form-group p-4" v-if="isCategorySelected">
                <div class="row">
                    <label class="col-12" for="subcategories">Виберіть категорю</label>
                    <select v-model="selectedSubcategory" class="col-12" id="subcategories">
                        <option value="" disabled selected></option>
                        <option value="">Підкатегорія !</option>
                        <option value="">Підкатегорія 2</option>
                        <option value="">Підкатегорія 3</option>
                        <option value="">Підкатегорія 4</option>
                    </select>
                </div>
            </div>
            <div class="col-12 f-form-group">
                <label for="description">Опис</label>
                <textarea rows="5" v-model="description" id="description"></textarea>
            </div>
            <div class="col-12">
                <div class="row justify-content-center">
                    <div class="col-sm-auto text-center f-submit">Додати товар</div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    export default {
        computed: {
            isPhotoSelected() {
                return this.imageSrc !== '';
            },
            isCategorySelected() {
                return this.selectedCategory !== '';
            }
        },
        data() {
            return {
                name: '',
                priceForMeasurementUnit: '',
                selectedMeasurementUnit: '',
                selectedCategory: '',
                selectedSubcategory: '',
                image: '',
                imageSrc: '',
                description: ''
            }
        },
        methods: {
            onChange(e) {
                if (e.target.files && e.target.files[0]) {

                    this.imageSrc = URL.createObjectURL(e.target.files[0]);

                    // let reader = new FileReader();
                    //
                    // reader.onload = function(e2) {
                    //     this.imageSrc = e2.target.result;
                    //
                    //     alert(this.imageSrc)
                    // };
                    // reader.readAsDataURL(e.target.files[0]);
                }
            }
        }
    }
</script>

<style scoped>
    @import "../../assets/css/addProduct.css";
    @import "../../assets/css/inputs.css";
</style>
