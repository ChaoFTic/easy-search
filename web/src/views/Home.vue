<template>
  <search-bar :advanced="true"/>
</template>

<script>
  import SearchBar from '@/components/SearchBar'

  export default {
    name: 'Home',
    components: {
      SearchBar
    },
    mounted () {
    }
  }
</script>
<style scoped>
  .main-container {
    min-width: 400px;
  }
</style>
