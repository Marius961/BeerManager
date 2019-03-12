<template>
    <form class="col-12 pl-5 pr-5 pt-3">
        <div class="row f-form-group">
            <label class="col-12" for="username">Ім‘я користувача</label>
            <div class="col-12">
                <div class="row align-items-center">
                    <input class="col-12" id="username" type="text">
                    <i class="col-auto fas fa-user"></i>
                </div>
            </div>
        </div>
        <div class="row f-form-group">
            <label class="col-12" for="email">Емейл</label>
            <div class="col-12">
                <div class="row align-items-center">
                    <input class="col-12" id="email" type="email">
                    <i class="col-auto fas fa-envelope"></i>
                </div>
            </div>
        </div>
        <div class="row f-form-group">
            <label class="col-12" for="fullName">П. І. Б.</label>
            <div class="col-12">
                <div class="row align-items-center">
                    <input class="col-12" id="fullName" type="email">
                    <i class="col-auto fas fa-address-card"></i>
                </div>
            </div>
        </div>
        <div class="row f-form-group">
            <label class="col-12" for="password">Пароль</label>
            <div class="col-12">
                <div class="row align-items-center">
                    <input class="col-12" id="password" type="password">
                    <i class="col-auto fas fa-unlock-alt"></i>
                </div>
            </div>
        </div>
        <div class="row f-form-group">
            <label class="col-12" for="confirmPassword">Підтвердіть пароль</label>
            <div class="col-12">
                <div class="row align-items-center">
                    <input class="col-12" id="confirmPassword" type="password">
                    <i class="col-auto fas fa-unlock-alt"></i>
                </div>
            </div>
        </div>
        <div class="row mt-2">
            <button type="submit" class="col-12 text-center p-btn p-btn-primary p-2">Зареєструватись</button>
        </div>
    </form>
</template>

<script>
    export default {
        name: "Registration"
    }
</script>

<style scoped>
    @import "../assets/css/forms.css";
</style>
