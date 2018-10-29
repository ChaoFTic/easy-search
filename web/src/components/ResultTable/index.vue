<template>
  <el-table :data="data">
    <el-table-column type="expand">
      <template slot-scope="{row}">
        <el-card>
          <div>Post</div>
          <div class="review-content" v-html="content_formatter(row.content)"></div>
        </el-card>
      </template>
    </el-table-column>
    <template v-for="(category, index) in categories" v-if="category.show">
      <el-table-column :width="category.width" :label="category.label" :prop="category.value" :key="index"/>
    </template>
  </el-table>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    name: 'ResultTable',

    props: {
      data: {
        type: Array,
        default: () => []
      }
    },

    computed: {
      ...mapState(['categories']),
    },

    methods: {
      content_formatter (content) {
        let result = ''
        content
          .split('\n')
          .map(paragraph => {
            return '<p>' + paragraph + '</p>'
          })
          .forEach(paragraph => {
            result = result + paragraph
          })
        return result
      }
    }
  }
</script>

<style scoped>
  .review-content {
    border: 2px black;
    margin-top: 10px;
    text-indent: 2em;
    max-height: 200px;
    overflow-y: scroll;
    margin-left: 10px;
  }

  .review-content::-webkit-scrollbar {
  }
</style>
