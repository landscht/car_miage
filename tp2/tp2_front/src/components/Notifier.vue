<template>
  <div class="text-center ma-2">
    <v-snackbar
        v-model="snackbar"
    >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
            color="pink"
            text
            v-bind="attrs"
            @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
export default {
  name: "Notifier",
  props: {
    text: String,
    snackbar: Boolean
  }
}
</script>

<style scoped>

</style>