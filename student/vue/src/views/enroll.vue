<template>
  <div>
    <div class="login-container">
      <div class="login-box" style="width: 350px">
        <div class="login-logo">
          <img src="@/assets/imgs/logo.png">
        </div>
        <el-form :model="data.form" ref="fromRef" :rules="rules" label-width="auto" style="max-width: 600px">
          <el-form-item prop="username">
            <el-input prefix-icon="User" v-model="data.form.username" placeholder="请输入账号"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input show-password prefix-icon="Lock" v-model="data.form.password" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item prop="name">
            <el-input prefix-icon="Avatar" v-model="data.form.name" placeholder="请输入姓名"/>
          </el-form-item>
          <el-form-item prop="phonenumber">
            <el-input prefix-icon="Phone" v-model="data.form.phonenumber" placeholder="请输入手机号"/>
          </el-form-item>
          <el-form-item prop="email">
            <el-input prefix-icon="Message" v-model="data.form.email" placeholder="请输入邮箱"/>
          </el-form-item>
          <el-form-item>
            <el-select v-model="data.form.sex" style="width: 100%">
              <el-option value="男" label="男"></el-option>
              <el-option value="女" label="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="bith">
            <el-date-picker v-model="data.form.bith" type="date" placeholder="选择生日" style="width: 100%"></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%;background-color: dodgerblue" @click="enroll">
              <el-icon><Expand /></el-icon>注 册
            </el-button>
          </el-form-item>
        </el-form>
        <div style="margin-top: 30px;text-align: right">
          <a href="/login">登录</a>
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
  form: {
    role:'STUDENT'
  }
})

const rules = reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
  ],
  phonenumber: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
  ],
})

const fromRef = ref()

const enroll = () => {
  fromRef.value.validate((valid) => {
    if (valid) {
      request.post('/enroll',data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success('注册成功')     //显示注册成功
          location.href='/login'     //跳转登录页面
        } else{
          ElMessage.error(res.msg )
        }
      })
    }
  })
  const loading = ElLoading.service({
    lock: true,
    text: '注册中',
    background: 'rgba(0, 0, 0, 0.7)',
  })          //注册加载转圈
  setTimeout(() => {
    loading.close()
  }, 2000)          //注册加载转圈延时
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