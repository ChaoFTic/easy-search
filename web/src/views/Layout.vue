<template>
  <div class="wrapper">
    <common-navbar/>
    <el-row type="flex" justify="center" align="middle">
      <el-col :span="12" class="main-container">
        <router-view></router-view>
      </el-col>
    </el-row>
    <div class="tool-bar">
      <el-button :circle="true" icon="el-icon-caret-top" @click="back_to_top">
      </el-button>
      <br/>
    </div>
  </div>
</template>

<script>
  import CommonNavbar from '@/components/CommonNavbar'
  import FaIcon from '@/components/FaIcon'

  export default {
    name: 'Layout',
    components: {
      CommonNavbar,
      FaIcon
    },

    methods: {
      back_to_top () {
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        })
      },
    }
  }
</script>

<style scoped>
  .tool-bar {
    position: fixed;
    bottom: 100px;
    right: 100px;

    display: flex;
    flex-direction: column;
  }
</style>
