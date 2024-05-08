<template>
  <div>
    <div class="login-container">
      <div class="login-box" style="width: 350px">
        <div class="login-logo">
          <img src="@/assets/imgs/logo.png">
        </div>
        <h4 style="text-align: center">信息验证</h4>
        <el-form :model="data.form" ref="fromRef" :rules="rules" label-width="auto" style="max-width: 600px">
          <el-form-item prop="username">
            <el-input prefix-icon="User" v-model="data.form.username" placeholder="请输入你要找回密码的账号"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%;background-color: dodgerblue" @click="Reset">
              <el-icon><DArrowRight /></el-icon>下一步
            </el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-dialog style="width: 35%;text-align: center" v-model="data.formVisible" title="修改密码">
        <el-form ref="passwordForm" :rules="rules" label-width="120px" label-position="right">
          <el-form-item label="请输入手机号" prop="phonenumber">
            <el-input v-model="data.form.phonenumber" placeholder="请输入账号绑定的手机号码"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword">
            <el-input v-model="data.newPassword" type="password" placeholder="请输入新密码"></el-input>
          </el-form-item>
          <el-form-item label="确认新密码" prop="confirmPassword">
            <el-input v-model="data.confirmPassword" type="password" placeholder="请确定新密码"></el-input>
          </el-form-item>
          <div>
            <el-button @click="data.formVisible = false">取消</el-button>
            <el-button type="primary" @click="SavePassword">保存</el-button>
          </div>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
import request from "../utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const data = reactive({
  form: {},
  formVisible:false,
  newPassword: '',
  confirmPassword:''
})

const rules = reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ]
})

const Reset = () => {
  request.post('/students/reset', {username: data.form.username}).then(res => {
    if (res.code === '200') {
      ElMessage.success("信息验证成功")
      data.formVisible = true
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}

const SavePassword = () => {
  //判定两次密码是否一致
  if (data.newPassword !== data.confirmPassword) {
    ElMessage.error("两次密码输入不一致");
    return;
  }
  request.post('/students/SavePassword', {username: data.form.username, phonenumber: data.form.phonenumber,newPassword: data.newPassword }).then(res => {
    if (res.code === '200') {
      ElMessage.success("密码修改成功")
      router.push('/login')
      data.formVisible = false
    }
    else {
      ElMessage.error(res.msg)
    }
  })
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