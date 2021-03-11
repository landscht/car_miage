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
        <v-text-field name="quantity" :v-model="quantity" type="number"  value="1" min="1" style="width: 35px"></v-text-field>
        {{quantity}}
        <p v-if="outOfStock" style="color: red; padding-top: 15px">Le produit n'est plus en stock</p>
        <p v-else style="padding-top: 15px"><br/></p>
      </v-card-text>
      <v-card-actions>
        <v-btn
            color="orange lighten-2"
            text
            @click="addToBasket(product)"
            :disabled="outOfStock"
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
    product: Object,
    outOfStock: Boolean
  },
  data() {
    return {
      productAdded: 'Le produit a bien été ajouté au panier.',
      snackbar: false,
      quantity: 1
    }
  },
  methods : {
    addToBasket(product){
      const purchase = {
        product : product,
        quantity : this.quantity
      }
      console.log(purchase)
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