<template>
  <el-form :rules="rules" class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import {postRequest} from '../utils/api'
  export default{
    data(){
      return {
        //登录验证规则
        rules: {
          account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}],
        },
        //提交表单
        loginForm: {
          username: 'sang',
          password: '123'
        },
        //记住密码
        checked:false,
      }
    },
    methods: {
      submitClick: function () {
        let vm  = this;
        postRequest('/login',{
          username: vm.loginForm.username,
          password: vm.loginForm.password
        }).then(xhr=>{
          if (xhr.status == 200) {
            var json = xhr.data;
            if (json.status == 'success') {
              vm.$router.replace({path: '/home'});
            } else {
              vm.$alert('登录失败!', '失败!');
            }
          }else {
            vm.$alert('登录失败!', '失败!');
          }
        }, resp=> {
          vm.$alert('找不到服务器⊙﹏⊙∥!', '失败!');
        })
      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
