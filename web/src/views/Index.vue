<template>
  <div class="table-wrapper">
    <el-table :data="tableData">
      <el-table-column prop="word" label="关键字"></el-table-column>
      <el-table-column prop="docList.length" label="频率"></el-table-column>
      <el-table-column prop="docList" label="文档列表">
        <template slot-scope="{ row }">
          <span v-html="row['docList'].map(docItem => docItem['docId']).join('->')"></span>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :page-size="Number(page_size)"
      :total="index.length"
      layout="slot,prev,pager, next, jumper, total"
      @current-change="pageHandler">
      <span class="page-size-controller">
        <span>每页显示个数</span>
        <el-input v-model="page_size" size="mini" class="page-size-input"></el-input>
      </span>
    </el-pagination>

    <!--<a :href="$axios.defaults.baseURL + '/invertedIndex'" target="_blank" download="content.txt">
      <el-button>下载索引</el-button>
    </a>-->

  </div>
</template>

<script>
  import ElTableFilterPanel from 'element-ui/packages/table/src/filter-panel'

  export default {
    name: 'InvertedIndex',
    components: { ElTableFilterPanel },
    data () {
      return {
        index: [],
        current_page: 1,
        page_size: 10
      }
    },
    computed: {
      tableData () {
        if (this.index) {
          return this.index.slice((this.current_page - 1) * this.page_size, this.current_page * this.page_size)
        }
      }
    },
    mounted () {
      this.getIndex()
    },
    methods: {
      getIndex () {
        // let query = new URLSearchParams()
        // query.append('page', this.current_page)
        // query.append('pageSize', this.page_size)

        this.$axios.get('/index').then(res => {
          this.index = res.data.data
        }).catch(e => console.log(e))
      },
      pageHandler(value) {
        this.current_page = value
      }
    }
  }
</script>

<style scoped>
  .page-size-input {
    width: 50px;
  }

  .page-size-controller {
    margin-right: 15px;
    font-weight: 400;
  }
</style>
