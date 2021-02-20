<template>
  <div>
    <v-card
        class="mx-auto"
        max-width="344"
    >
      <v-card-title>{{product.name}} {{product.price}}€</v-card-title>
      <v-card-subtitle>{{product.description}}</v-card-subtitle>
      <v-card-text>
        <label>Quantité : </label>
        <input id="quantity" type="number"  value="1" min="1" style="width: 35px">
      </v-card-text>
      <v-card-actions>
        <v-btn
            color="orange lighten-2"
            text
            @click="addToBasket(product)"
        >
          Ajouter au panier
        </v-btn>
      </v-card-actions>
    </v-card>
    <Notifier :text="productAdded" :snackbar="snackbar"></Notifier>
  </div>
</template>

<script>
import Notifier from "@/components/Notifier";
export default {
  name: "ProductCard",
  components: {Notifier},
  props: {
    product: Object
  },
  data() {
    return {
      productAdded: 'Le produit a bien été ajouté au panier.',
      snackbar: false
    }
  },
  methods : {
    addToBasket(product){
      let quantity = document.getElementById('quantity').value;
      const purchase = {
        product : product,
        quantity : quantity
      }
      this.snackbar = true;
      if (localStorage.getItem('user_basket')) {
        let basket = JSON.parse(localStorage.getItem('user_basket'));
        basket.purchases.push(purchase);
        localStorage.setItem('user_basket', JSON.stringify(basket));
      } else {
        const basket = {
          purchases: [purchase]
        }
        localStorage.setItem('user_basket', JSON.stringify(basket));
      }
    }
  }
}
</script>

<style scoped>

</style>