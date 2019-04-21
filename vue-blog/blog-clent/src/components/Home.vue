<template>
  <el-container class="home_container">
    <el-header>
      <div class="home_title">V部落博客管理平台</div>
      <div class="home_userinfoContainer">
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link home_userinfo">
    {{currentUserName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
            <el-dropdown-item command="MyArticle">我的文章</el-dropdown-item>
            <el-dropdown-item command="MyHome">个人主页</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-openeds="['1']" @select="handleSelect">
          <template v-for="(item,index) in menuList">
            <el-submenu :index="item.children[0].path" v-if="item.children && item.children.length>1">
              <template slot="title">
                <i :class="item.iconCls"></i>
                <span>{{item.name}}</span>
              </template>
              <el-menu-item v-for="(child,index) in item.children" :index="child.path" :key="index">
                {{child.name}}
              </el-menu-item>
            </el-submenu>
            <template v-else>
              <el-menu-item :index="item.path">
                <i :class="item.iconCls"></i>
                <span slot="title">{{item.name}}</span>
              </el-menu-item>
            </template>
          </template>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="breadcrumb"></el-breadcrumb-item>
          </el-breadcrumb>
          <artcle_list v-if="com=='11'"></artcle_list>
          <post_article v-if="com=='12'"></post_article>
          <user_list v-if="com=='2'"></user_list>
          <cateMana_list v-if="com=='3'"></cateMana_list>
          <DataCharts v-if="com=='4'"></DataCharts>

        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>
<script>
  import {getRequest} from '../utils/api'
  import artcle_list from './ArticleList.vue'
  import post_article from './PostArticle.vue'
  import user_list from './UserMana.vue'
  import cateMana_list from './CateMana.vue'
  import DataCharts from './DataCharts.vue'

  export default{
    components:{
      artcle_list,
      post_article,
      user_list,
      cateMana_list,
      DataCharts
    },
    data(){
      return {
        com:'11',
        breadcrumb:'用户管理',
        currentUserName: '',
        menuList:[
          {
            name:'文章管理',
            path:'1',
            iconCls:'fa fa-file-text-o',
            children:[
              {name:'文章列表', path:'11', iconCls:'',},
              {name:'发表文章', path:'12', iconCls:'',}
            ]
          },
          {
            name:'用户管理',
            path:'2',
            iconCls:'fa fa-user-o',
          },
          {
            name:'栏目管理',
            path:'3',
            iconCls:'fa fa-reorder',
          },
          {
            name:'数据统计',
            path:'4',
            iconCls:'fa fa-bar-chart',
          },
        ],
      }
    },
    mounted: function () {
      var _this = this;
      getRequest("/currentUserName").then(function (msg) {
        _this.currentUserName = msg.data;
      }, function (msg) {
        _this.currentUserName = '游客';
      });
    },
    methods: {
      handleCommand(command){
        var _this = this;
        if (command == 'logout') {
          this.$confirm('注销登录吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            getRequest("/logout")
            _this.currentUserName = '游客';
            _this.$router.replace({path: '/'});
          }, function () {
            //取消
          })
        }
      },
      handleSelect(key, keyPath) {
        let vm = this;
        vm.com = key;
        console.log("key==>",key, "keyPath==>",keyPath);
      }
    }
  }
</script>
<style>
  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-aside {
    background-color: #ECECEC;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }
</style>
