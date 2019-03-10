<template>
    <div class="row p-2 align-items-center justify-content-between mt-3 segment-box">
        <div class="col-auto">
            <span>Замовник</span>
            <span class="font-weight-bold">Іван Васильович</span>
        </div>
        <div class="col-auto">
            <div class="row">
                <button class="col-auto p-btn p-btn-primary">
                    <i class="fas fa-check"></i>
                    Прийняти
                </button>
                <button class="col-auto p-btn p-btn-danger">
                    <i class="fas fa-times"></i>
                    Відхилити
                </button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {

    }
</script>

<style scoped>
    @import "../assets/css/receivedOrderSegment.css";
</style>
