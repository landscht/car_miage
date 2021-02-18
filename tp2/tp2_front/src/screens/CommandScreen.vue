<template>
  <div style="padding-top: 50px" align="center">
    <h1>Vos commandes</h1>
    <v-data-table
        no-results-text="Vous n'avez aucune commande"
        :headers="headers"
        :items="commands"
        no-data-text="Vous n'avez aucune commande"
        :items-per-page="10"
        class="elevation-1"
    ></v-data-table>
  </div>
</template>

<script>

import CommandService from "@/services/CommandService";

export default {
  name: "CommandScreen",
  mounted() {
    this.getAllCommand(1);
  },
  data: () => ({
    headers: [
      {
        text: 'Date',
        align: 'start',
        value: 'date',
      },
      { text: 'Nombre d\'articles', value: 'nbArticles' },
      { text: 'Prix', value: 'price' }
    ],
    commands: []
  }),
  methods: {
    getAllCommand(page) {
      CommandService.getAllCommand(page).then((data) => {
        console.log(data);
        this.commands = data.data;
      })
    }
  }
}
</script>

<style scoped>

</style>