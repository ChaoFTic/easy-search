import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getters'

import advancedQuery from './modules/advanced_query'

Vue.use(Vuex)

export default new Vuex.Store({
  strict: process.env.NODE_ENV !== 'production',

  state: {
    categories: [{
      label: '博主性别',
      value: 'blogger.gender',
      show: true,
    }, {
      label: '博主年龄',
      value: 'blogger.age',
      show: true
    }, {
      label: '博主星座',
      value: 'blogger.astrologicalSign',
      show: true,
    }, {
      label: '博主类别',
      value: 'blogger.industry',
      show: true
    }, {
      label: '发布时间',
      value: 'date',
      show: true
    }, {
      label: '博客内容',
      value: 'content',
      show: false
    }]
  },
  actions: {},
  mutations: {},
  modules: {
    advancedQuery
  },
  getters: getters
})
