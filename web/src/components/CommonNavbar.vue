<template>
  <el-menu :default-active="$route.path" mode="horizontal" router>

    <template v-for="(route, index) in commonRoute">
      <el-menu-item :index="route.path" :key="index">
        <span v-html="route.name"></span>
      </el-menu-item>
    </template>

  </el-menu>
</template>

<script>
  import commonRoute from '@/router/commonRoute';
  export default {
    name: 'CommonNavbar',
    data () {
      return {
        commonRoute: commonRoute
      }
    }
  }
</script>

<style scoped>

</style>
