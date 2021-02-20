<template>
  <div>
    <v-app-bar
        dense
        dark
    >
      <v-btn @click="goToHomeScreen">
        Amazon
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn to="/products">
        Les produits
      </v-btn>
      <v-btn to="/commands">
        Mes commandes
      </v-btn>
      <v-btn to="/basket">
        Mon panier <v-icon style="padding-left: 10px">mdi-basket</v-icon>
      </v-btn>
      <v-btn>
        Me déconnecter
      </v-btn>
    </v-app-bar>
  </div>
</template>

<script>
import router from "@/router/router";
export default {
  name: "Header",
  methods: {
    goToHomeScreen(){
      router.push('/')
    }
  }
}
</script>

<style scoped>

</style>