<template>
  <div align="center">
    <v-container>
      <h1>
        Produits
      </h1>
      <v-row>
        <v-col
            v-for="product in products"
            v-bind:key="product.id"
        >
          <ProductCard
              :product="product"
          ></ProductCard>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";
import ProductCard from "@/components/ProductCard";
export default {
  name: "ProductScreen",
  components: {ProductCard},
  mounted() {
    this.getAllProducts(0);
  },
  data() {
    return {
      products: []
    }
  },
  methods: {
    getAllProducts(page){
      ProductService.getAllProducts(page).then(response => {
        console.log(response.data);
        this.products = response.data;
      })
    }
  }
}
</script>

<style scoped>

</style>