<template>
  <div class="wrapper">

    <div class="logo">
      <span><b>Blog</b>搜索</span>
    </div>

    <el-input v-show="!show_advance" v-model="basic_query_string" type="text" @keyup.enter.native="basic_search"/>
    <el-row class="search-toolbar" type="flex" justify="center">
      <div>
        <el-button v-show="!show_advance" type="default" native-type="submit" icon="el-icon-search"
                   style="margin: 0 10px" @click="basic_search">搜索
        </el-button>
        <!--<el-button type="text" style="margin: 0;" @click="toggle_advance">高级搜索</el-button>-->
      </div>
      <br/>
      <el-popover
        placement="bottom"
        title="语法帮助"
        trigger="click">
        <span>
          "将搜索条件之间用&，|，！隔开<br/>
          &，|，!分别表示与、或、非<br/>
          例如：日本！森林<br/>
          日本&电影<br/>
          注意：每个符号的作用范围仅限紧跟在符号之后的条件"
        </span>
        <el-button slot="reference" type="text">帮助提示</el-button>
      </el-popover>
    </el-row>


    <!--<advanced-search v-if="advanced" v-show="show_advance" ref="advanced"/>-->

  </div>
</template>

<script>
  import AdvancedSearch from './AdvancedSearch'
  import AdvancedSearchItem from './AdvancedSearchItem'

  export default {
    name: 'SearchBar',
    components: {
      AdvancedSearch,
      AdvancedSearchItem
    },
    props: {
      advanced: {
        type: Boolean,
        default: false
      }
    },
    data () {
      return {
        show_advance: false,
        basic_query_string: ''
      }
    },
    methods: {
      toggle_advance () {
        this.show_advance = !this.show_advance
      },
      basic_search () {
        this.$router.push({
          path: '/result',
          data: {
            advanced: false
          },
          query: {
            qs: this.basic_query_string,
            advanced: false
          }
        })
      }
    }
  }
</script>
<style scoped>
  .wrapper {
    margin-top: 150px;
  }

  .search-toolbar {
    margin-top: 20px;
  }

  .logo {
    text-align: center;
    color: #007711;
    font-weight: bolder;
    font-size: 30px;
    margin-bottom: 30px;
  }

  .logo > span > b {
    font-size: 48px;
    margin: 5px
  }
</style>
