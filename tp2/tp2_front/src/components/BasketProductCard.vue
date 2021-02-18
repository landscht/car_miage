<template>
  <div>
    <v-card
        class="mx-auto"
        width="344"
    >
      <v-card-title>{{name}} {{price}}€</v-card-title>
      <v-card-subtitle>{{description}}</v-card-subtitle>
      <v-card-actions>
        <v-btn
            color="orange lighten-2"
            text
            @click="removeFromBasket(id)"
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
    name: String,
    price: Number,
    description: String,
    image: Object,
    id: Number,
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