<template>
  <div>
    <result-table :data="result_data"></result-table>
  </div>
</template>

<script>
  import ResultTable from '@/components/ResultTable'
  import InvertedIndex from '@/views/Index'

  export default {
    name: 'Result',
    components: {
      ResultTable,
      InvertedIndex
    },
    data () {
      return {
        result_data: []
      }
    },
    computed: {
      conditions () {
        let condition = this.$route.query['qs']
        return condition.split(RegExp(/[&|!！]/))
      },
    },
    mounted () {
      this.get_data()
    },
    methods: {
      get_data () {
        let params = new URLSearchParams()
        for (let queryKey in this.$route.query) {
          params.append(queryKey, this.$route.query[queryKey])
        }
        this.$axios
          .post('/search', params)
          .then(res => {
            this.result_data = res.data.data
          })
          .catch(err => {
            console.log(err)
          })
      }
    }
  }
</script>

<style scoped>
  .main-container {
    min-width: 750px;
  }
</style>
