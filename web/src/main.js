import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from './axios'

import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
import 'font-awesome/css/font-awesome.css'

Vue.use(ElementUI)

Vue.prototype.$axios = axios

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  render: h => h(App),
  router: router,
  store: store
})
