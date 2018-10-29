export default {
  state: {
    conditions: []
  },
  mutations: {
    ADD_CONDITION (state, condition) {
      state.conditions[condition.id] = condition
    },
    REMOVE_CONDITION (state, id) {
      state.conditions.splice(id, 1)
    }
  },
  actions: {
    add_condition ({ commit }, condition) {
      commit('ADD_CONDITION', condition)
    },
    remove_condition ({ commit }, id) {
      commit('REMOVE_CONDITION', id)
    }
  }
}
