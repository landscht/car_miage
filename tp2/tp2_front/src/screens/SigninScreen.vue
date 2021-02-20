<template>
  <div style="padding-top: 50px" align="center">
    <v-card
        class="mx-auto"
        max-width="600"
    >
      <v-img
          class="white--text align-end"
          height="200px"
          src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>
          Veuillez vous connecter
        </v-card-title>
      </v-img>

      <v-card-text class="text--primary">
        <v-form
            ref="form"
            v-model="formValid"
            lazy-validation
        >
          <v-text-field
              v-model="auth.email"
              label="Email"
              :rules="emailRules"
              required
          ></v-text-field>

          <v-text-field
              v-model="auth.password"
              :rules="pwdRules"
              :type="viewMdp ? 'text' : 'password'"
              label="Mot de passe"
              required
          >
            <v-icon
                slot="append"
                @click="viewMdp = !viewMdp"
            >
              {{ viewMdp ? 'mdi-eye-off' : 'mdi-eye'}}
            </v-icon>
          </v-text-field>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-btn
            :disabled="!formValid"
            color="orange"
            outlined
        >
          Se connecter
        </v-btn>

        <v-btn
            color="orange"
            text
            to="/signup"
        >
          Je n'ai pas de compte
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SigninScreen",
  data: () => ({
    auth: {
      email: '',
      password: ''
    },
    emailRules: [
      v => !!v || 'L\'email est requis',
    ],
    pwdRules: [
      v => !!v || 'Le mot de passe est requis',
    ],
    formValid: false,
    viewMdp: false
  }),
  mounted() {
    // je mets à la mano un user de ma bdd dans le local storage pour faire genre il s'est authent, le temps que tu finisses
    /*localStorage.clear()
    let user = {
      id:5,
      firstname: 'Gabriel',
      lastname: 'Congin',
      commands: []
    }
    localStorage.setItem('current_user', JSON.stringify(user));*/
    console.log(localStorage.getItem('current_user'))
  }
}
</script>

<style scoped>

</style>