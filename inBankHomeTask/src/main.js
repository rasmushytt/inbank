import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import router from "@/router";

const app = createApp({
  extends: App,
})
app.use(router)
app.use(vuetify)
app.use(VueAxios, axios)

app.mount('#app')
