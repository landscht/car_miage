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
          Veuillez vous inscrire
        </v-card-title>
      </v-img>

      <v-card-text class="text--primary">
        <v-form
            ref="form"
            v-model="formValid"
            lazy-validation
        >
          <v-text-field
              v-model="auth.user.firstname"
              label="Prénom"
              :rules="firstnameRules"
              required
          ></v-text-field>

          <v-text-field
              v-model="auth.user.lastname"
              label="Nom"
              :rules="lastnameRules"
              required
          ></v-text-field>

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
              ref="password"
              required
          >
            <v-icon
                slot="append"
                @click="viewMdp = !viewMdp"
            >
              {{ viewMdp ? 'mdi-eye-off' : 'mdi-eye'}}
            </v-icon>
          </v-text-field>

          <v-text-field
              v-model="confirmPassword"
              :rules="confirmRules"
              :type="viewMdp ? 'text' : 'password'"
              validate
              label="Confirmation"
              required
          ></v-text-field>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-btn
            color="orange"
            @click="signup"
            outlined
        >
          S'inscrire
        </v-btn>

        <v-btn
            color="orange"
            text
            to="/signin"
        >
          J'ai déja un compte
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import AuthService from "@/services/AuthService";

export default {
  name: "SignupScreen",
  data: () => ({
    auth: {
      email: '',
      password: '',
      user: {
        lastname: '',
        firstname: ''
      }
    },
    confirmPassword: '',
    emailRules: [
      v => !!v || 'L\'email est requis',
    ],
    firstnameRules: [
      v => !!v || 'Le prénom est requis',
    ],
    lastnameRules: [
      v => !!v || 'Le nom est requis',
    ],
    pwdRules: [
      v => !!v || 'Le mot de passe est requis',
    ],
    confirmRules: [
      v => !!v || 'Veuillez confirmer votre mot de passe',
    ],
    formValid: false,
    viewMdp: false
  }),
  methods: {
    signup() {
      if (this.$refs.form.validate()) {
        AuthService.register(this.auth).then(() => {
          this.$router.push('/signin');
        })
            .catch(() => {
              console.log("erreur");
            });
      }
    }
  },
}
</script>

<style scoped>

</style>