<template>
  <div>
    <div class="login-container">
      <div class="login-box" style="width: 350px">
        <div class="login-logo">
          <img src="@/assets/imgs/JXHJ-logo.png">
        </div>
        <el-form :model="data.form" ref="fromRef" :rules="rules" label-width="auto" style="max-width: 600px">
          <el-form-item prop="username">
            <el-input prefix-icon="User" v-model="data.form.username" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input show-password prefix-icon="Lock" v-model="data.form.password" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item>
            <el-select v-model="data.form.role" style="width: 100%">
              <el-option value="STUDENTS" label="学生"></el-option>
              <el-option value="ADMIN" label="管理员"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%;background-color: dodgerblue" @click="login">
              <el-icon><Expand /></el-icon>登 入
            </el-button>
          </el-form-item>
        </el-form>
        <div style="margin-top: 30px;">
          <div style="display: inline-block; margin-right: 192.67px;">
            <a href="/enroll">注册</a>
          </div>
          <div style="display: inline-block;">
            <a href="/back">忘记密码</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive,ref} from "vue";
import request from "../utils/request";
import {ElMessage} from "element-plus";
import {ElLoading} from 'element-plus';


const fullscreenLoading = ref(false)

const data = reactive({
  form: {role:"STUDENTS"}
})

const rules = reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ],
})

const fromRef = ref()

const login = () => {
  fromRef.value.validate((valid) => {
    if (valid) {
      request.post('/login',data.form).then(res => {
        if (res.code === '200') {
          localStorage.setItem('student-user',JSON.stringify(res.data))   //把数据放入缓存
          ElMessage.success('登陆成功')     //显示登录成功
          location.href='/home'     //跳转主页
        } else{
          ElMessage.error(res.msg )
        }
      })
    }
  })
  const loading = ElLoading.service({
    lock: true,
    text: '登陆中',
    background: 'rgba(0, 0, 0, 0.7)',
  })          //login加载转圈
  setTimeout(() => {
    loading.close()
  }, 2000)          //login加载转圈延时
}

</script>


<style scoped>
.login-container {
  min-height: 100vh;
  overflow:hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("@/assets/imgs/JXHJXY.png");
  background-size: cover;
}
.login-box {
  background-color: rgba(255,255,255,.3);
  box-shadow: 0 0 10px rgba(0,0,0,.1);
  border: 1px solid #dddddd;
  padding: 30px;
  border-radius: 5px;
}
.login-logo {
  text-align: center;
  margin-bottom: 30px;
}
img{
  width: 50px;
  height: 50px;
}
</style>