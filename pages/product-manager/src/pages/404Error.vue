<template>
  <div class="container">
    <div class="row">
      <div class="col-12 error-code">404</div>
      <div class="col-12 error-description">Сторінку не знайдено</div>
    </div>
    <div class="row justify-content-center">
      <router-link to="/" class="col-auto">На головну</router-link>
    </div>
  </div>
</template>

<script>
    export default {
    }
</script>

<style scoped>
  .error-code {
    text-align: center;
    font-size: 8em;
    font-weight: bolder;
  }

  .error-description {
    text-align: center;
    font-size: 2em;
  }

  .link {


  }
</style>
