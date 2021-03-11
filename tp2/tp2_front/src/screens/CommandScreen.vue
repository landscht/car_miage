<template>
  <div style="padding-top: 50px" align="center">
    <h1>Vos commandes</h1>
    <v-data-table
        no-results-text="Vous n'avez aucune commande"
        :headers="headers"
        :items="commands"
        no-data-text="Vous n'avez aucune commande"
        :items-per-page="10"
        show-expand
        class="elevation-1"
    >
      <template v-slot:item.date="{item}">
        {{getFullDate(item.date)}}
      </template>
      <template v-slot:item.nbArticles="{item}">
        {{getNbArticles(item)}}
      </template>
      <template v-slot:item.price="{item}">
        {{getPrice(item)}}€
      </template>
      <template v-slot:expanded-item="{ headers, item }">
        <div v-for="purchase in item.purchases" v-bind:key="purchase.id" style="width: 100%">
          <p>
            {{purchase.product.name}} x {{purchase.quantity}}
          </p>
        </div>
      </template>
    </v-data-table>
  </div>
</template>

<script>

import CommandService from "@/services/CommandService";
import DateService from "@/services/DateService";

export default {
  name: "CommandScreen",
  mounted() {
    this.getAllCommand(0);
  },
  data: () => ({
    headers: [
      { text: 'Numéro de commande', value: 'id'},
      { text: 'Date', value: 'date'},
      { text: 'Nombre d\'articles', value: 'nbArticles' },
      { text: 'Prix', value: 'price' }
    ],
    commands: []
  }),
  methods: {
    getFullDate(timestamp) {
      return DateService.getFullDate(timestamp);
    },
    getNbArticles(command) {
      return command.purchases.reduce((a, b) => a + b.quantity, 0);
    },
    getPrice(command) {
      console.log(command.purchases)
      return command.purchases.reduce((a, b) => a + (b.product.price * b.quantity), 0);
    },
    getAllCommand(page) {
      CommandService.getAllCommand(page).then((data) => {
        console.log(data.data);
        this.commands = data.data;
      })
    }
  }
}
</script>

<style scoped>

</style>