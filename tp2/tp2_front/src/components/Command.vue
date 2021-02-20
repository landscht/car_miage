<template>
  <div>
    <h2>Commande {{command.id}}</h2>
    <div
        v-for="purchase in command.purchases"
        v-bind:key="purchase.id"
    >
      <BasketProductCard :purchase="purchase"></BasketProductCard>
    </div>
  </div>
</template>

<script>
import BasketProductCard from "@/components/BasketProductCard";
export default {
  name: "Command",
  components: {BasketProductCard},
  props:{
    command: Object
  }
}
</script>

<style scoped>

</style>