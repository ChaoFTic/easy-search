<template>
  <div class="adv-search-wrapper">
    <el-card>
      <div slot="header" class="clearfix">
        <span>高级搜索条件<b>还未完成</b></span>
        <div class="right-left-tools" style="float:right;">
          <el-button class="no-padding" icon="fa fa-plus" type="text" @click="add_condition"></el-button>
          <el-button class="no-padding" icon="fa fa-minus"
                     type="text" @click="remove_condition(condition_list[condition_list.length - 1])"></el-button>
        </div>
      </div>

      <div class="card-body">
        <template v-for="(condition, index) in condition_list">
          <advanced-search-item :key="index" :first="index===0" :condition.sync="condition"></advanced-search-item>
        </template>
      </div>

      <div class="card-footer">
        <el-button @click="add_condition">添加一个条件</el-button>
        <el-button class="pull-right search-button" type="default" icon="el-icon-search" @click="start_query">高级搜索
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import AdvancedSearchItem from './AdvancedSearchItem'
  import {generateQueryString} from '@/utils'

  export default {
    name: 'AdvancedSearch',
    components: {
      AdvancedSearchItem
    },
    data () {
      return {
        condition_list: [
          {
            id: 1,
            operator: ''
          }
        ]
      }
    },
    computed: {},
    methods: {
      add_condition () {
        this.condition_list.push({
          id: this.condition_list.length + 1,
          operator: '|'
        })
      },
      remove_condition (condition) {
        if (this.condition_list.length <= 1) {

        } else {
          this.condition_list.splice(this.condition_list.findIndex(cond => cond === condition), 1)
          let index = 1
          this.condition_list.forEach(cond => {
            cond.id = index
            index++
          })
        }
      },
      start_query () {
        let queryString = generateQueryString(this.condition_list)
        this.$router.push({
          path: '/result',
          query: {
            qs: queryString,
            advanced: true
          }
        })
      }
    }
  }
</script>

<style scoped>
  .adv-search-wrapper {
    margin-top: 20px;
  }

  .no-padding {
    padding: 0;
  }

  .card-footer > .search-button {
    width: 200px;
    text-align: center;
  }
  span > b {
    font-size: 20px;
    color: red;
  }
</style>
