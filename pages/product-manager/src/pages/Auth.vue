<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4 p-0 pb-3 form-box">
                <div class="row">
                    <div class="col-12 p-0">
                        <div class="row">
                            <router-link to="login" active-class="tab-active" class="col-6 form-tab text-center">Вхід</router-link>
                            <router-link to="registration" active-class="tab-active" class="col-6 form-tab text-center">Реєстрація</router-link>
                        </div>
                    </div>
                    <transition name="fadeHeight">
                        <router-view></router-view>
                    </transition>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {

    }
</script>

<style scoped>
    @import "../assets/css/auth.css";
    @import "../assets/css/transitions.css";

    .fadeHeight-enter-active,
    .fadeHeight-leave-active {
        transition: all .5s;
        max-height: 600px;
    }
    .fadeHeight-enter,
    .fadeHeight-leave-to
    {
        margin: -20px;
        opacity: 0;
        max-height: 0;
    }
</style>
