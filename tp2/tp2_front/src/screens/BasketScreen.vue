<template>
  <div>
    <h1>Votre panier</h1>
    <div
        v-for="product in basket"
        v-bind:key="product.id"
        class="container"
    >
      <BasketProductCard
          :id="product.id"
          :name="product.name"
          :price="product.price"
          :description="product.description"
      ></BasketProductCard>
    </div>
  </div>
</template>

<script>
import BasketProductCard from "@/components/BasketProductCard";

export default {
  name: "BasketScreen",
  components: {BasketProductCard},
  data() {
    return {
      basket: Object
    }
  },
  mounted() {
    this.getBasket();
  },
  methods : {
    getBasket(){
      this.basket = JSON.parse(localStorage.getItem('user_basket'));
      console.log(this.basket);
    }
  }
}
</script>

<style scoped>
  .container{
    display: flex;
    justify-content: flex-start;
  }
</style>