<template>
  <div>
    <h1>Votre panier</h1>
    <div v-if="basket">
      <div
          v-for="purchase in basket.purchases"
          v-bind:key="purchase.product.id"
      >
        <BasketProductCard
            :purchase="purchase"
        ></BasketProductCard>
      </div>
      <div align="center">
        <v-btn @click="createCommand()" style="margin-top: 50px">Valider la commande</v-btn>
      </div>
    </div>
    <Notifier :text="productAdded" :snackbar="snackbar"></Notifier>
  </div>
</template>

<script>
import BasketProductCard from "@/components/BasketProductCard";
import Notifier from "@/components/Notifier";
import UserService from "@/services/UserService";
export default {
  name: "BasketScreen",
  components: {BasketProductCard, Notifier},
  data() {
    return {
      basket: Object,
      productAdded: 'Commande réussie, merci!',
      snackbar: false
    }
  },
  mounted() {
    this.getBasket();
  },
  methods : {
    getBasket(){
      this.basket = JSON.parse(localStorage.getItem('user_basket'));
      console.log(this.basket);
    },
    createCommand(){
      let user = JSON.parse(localStorage.getItem('current_user'));
      user.commands.push(this.basket);
      this.snackbar = true
      UserService.saveUser(user).then(response => {
        localStorage.setItem('current_user', JSON.stringify(response.data));
        localStorage.removeItem('user_basket');
      })
    }
  }
}
</script>

<style scoped>
</style>