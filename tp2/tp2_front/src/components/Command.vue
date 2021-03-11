<template>
  <div>
    <div
        v-for="purchase in command.purchases"
        v-bind:key="purchase.id"
    >
      {{purchase.product.name}} x {{product.quantity}}
    </div>
  </div>
</template>

<script>
export default {
  name: "Command",
  props:{
    command: Object
  },
}
</script>

<style scoped>

</style>