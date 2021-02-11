<template>
  <div>
    <v-card
        class="mx-auto"
        max-width="344"
    >
      <v-card-title>{{name}} {{price}}€</v-card-title>
      <v-card-subtitle>{{description}}</v-card-subtitle>
      <v-card-actions>
        <v-btn
            color="orange lighten-2"
            text
        >
          Acheter
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "ProductCard",
  props: {
    name: String,
    price: Number,
    description: String,
    image: Object
  }
}
</script>

<style scoped>

</style>