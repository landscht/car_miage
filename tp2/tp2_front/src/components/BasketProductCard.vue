<template>
  <div>
    <v-card
        class="mx-auto"
        width="344"
    >
      <v-card-title>{{purchase.product.name}} {{purchase.product.price}}€</v-card-title>
      <v-card-subtitle>{{purchase.product.description}}</v-card-subtitle>
      <v-card-text>
        <label>Quantité : </label>
        <input id="quantity" type="number"  :value="purchase.quantity" :min="purchase.quantity" style="width: 35px">
      </v-card-text>
      <v-card-actions>
        <v-btn
            color="orange lighten-2"
            text
            @click="removeFromBasket(purchase.product.id)"
        >
          Supprimer
        </v-btn>
      </v-card-actions>
    </v-card>
    <Notifier :text="productDeleted" :snackbar="snackbar"></Notifier>
  </div>
</template>

<script>
import Notifier from "@/components/Notifier";

export default {
  name: "BasketProductCard",
  components: {Notifier},
  props: {
    purchase: Object
  },
  data() {
    return {
      productDeleted: 'Le produit a bien été supprimé du panier.',
      snackbar: false
    }
  },
  methods : {
    removeFromBasket(id) {
      this.snackbar = true;
      let basket = JSON.parse(localStorage.getItem('user_basket'));
      basket.forEach(function(item, index) {
        if(item.id === id){
          basket.splice(index,1);
          localStorage.setItem('user_basket',JSON.stringify(basket));
        }
      })
    }
  }
}
</script>

<style scoped>

</style>