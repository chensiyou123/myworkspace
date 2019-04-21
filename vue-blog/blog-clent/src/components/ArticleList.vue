<template>
  <el-container class="article_list">
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="全部文章" name="all">

        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
  import BlogTable from './BlogTable'

  export default {
    name: "ArticleList",
    components: {
      'blog_table': BlogTable,
    },
    data(){
      return{
        activeName:'',
      }
    },
    methods:{
      handleClick(){

      },
    },
    components: {

    },
  }
</script>

<style scoped>

</style>
