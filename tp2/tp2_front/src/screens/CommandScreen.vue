<template>
  <div>
    <h1>Commandes :</h1>
    <div
        v-for="command in commands"
        v-bind:key="command.id"
    >
      <Command :command="command"></Command>
    </div>
  </div>
</template>

<script>
import Command from "@/components/Command";
export default {
  name: "CommandScreen",
  components: {Command},
  mounted() {
    this.getCommands();
  },
  data() {
    return {
      commands: []
    }
  },
  methods: {
    getCommands(){
      this.commands = JSON.parse(localStorage.getItem('current_user')).commands;
    }
  }
}
</script>

<style scoped>

</style>