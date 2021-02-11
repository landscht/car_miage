<template>
  <v-app>
    <div>
      <Header></Header>
      <router-view/>
    </div>
  </v-app>
</template>

<script>

import Header from "@/components/Header";
export default {
  name: 'App',
  components: {
    Header
  }
}
</script>

<style>
</style>
