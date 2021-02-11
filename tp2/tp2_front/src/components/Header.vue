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
      <v-btn>
        Mes commandes
      </v-btn>
      <v-btn>
        Mon panier
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