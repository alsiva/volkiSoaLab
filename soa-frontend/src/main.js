import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { UrlService } from './scripts/urlService'

const app = createApp(App)
const base_url = "http://localhost:8080"
const urlService = new UrlService(base_url)

app.use(router)

app.mount('#app')

export {urlService};
