<template>
  <i :class="['fa', 'fa-' + icon, 'fa-' + scale + 'x', 'fa-rotation-' + degree ]"></i>
</template>

<script>
  import 'font-awesome/css/font-awesome.css'
  export default {
    name: 'FaIcon',
    props: {
      icon: {
        type: String,
        default: '',
        require: true
      },
      scale: {
        type: Number,
        default: 1,
      },
      degree: {
        type: String,
        default: '',
        validate: (deg) => ['90', '180', '270'].indexOf(deg) !== -1
      }

  }
  };
</script>

<style scoped>

</style>
