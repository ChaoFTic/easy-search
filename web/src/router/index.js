import Vue from 'vue'
import Router from 'vue-router'
import commonRoute from './commonRoute'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    component: () => import('@/views/Layout'),
    children: commonRoute
  }]
})
