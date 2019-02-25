<template>
  <div class="row">
    <div class="col-auto font-weight-light">{{title}}:</div>
    <div class="col font-weight-bold">
      <slot>{{value}}</slot>
    </div>
  </div>
</template>

<script>
    export default {
      props: {
        title: String,
        value: String
      }
    }
</script>

<style scoped>

</style>
