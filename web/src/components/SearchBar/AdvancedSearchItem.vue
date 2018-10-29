<template>
  <div class="adv-search-item-wrapper">
    <el-select v-if="!first" v-model="condition.operator" class="query-operator-selector"
               value="" @change="update_condition(condition)">
      <template v-for="(operator, index) in operator_list">
        <el-option :key="index" :label="operator.label" :value="operator.value" disabled></el-option>
      </template>
    </el-select>
    <br/>
    <el-select v-model="condition.category" class="query-category-selector" value="" @change="update_condition(condition)">
      <el-option
        v-for="(category, index) in categories"
        :label="category.label"
        :value="category.value"
        :key="index"></el-option>
    </el-select>
    <div>
      <el-date-picker
        v-if="condition.category==='time'"
        v-model="condition.value"
        type="daterange"
        value-format="timestamp"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        @change="update_condition(condition)">
      </el-date-picker>
      <el-input v-else v-model="condition.value" @change="update_condition(condition)"/>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    name: 'AdvancedSearchItem',
    props: {
      first: {
        type: Boolean,
        default: false
      },
      condition: {
        type: Object,
        default: () => {
        }
      }
    },
    data () {
      return {
        operator_list: [
          {
            label: '或',
            value: '|'
          },
          {
            label: '且',
            value: '&'
          }
        ]
      }
    },
    computed: {
      ...mapState(['categories'])
    },
    methods: {
      update_condition (newCondition) {
        this.$emit('update:condition', newCondition)
      }
    }
  }
</script>
<style scoped>
  .query-operator-selector {
    width: 80px;
    margin-bottom: 10px;
  }

  .adv-search-item-wrapper {
    margin-bottom: 10px;
  }

  .query-category-selector {
    margin-bottom: 5px;
  }
</style>
