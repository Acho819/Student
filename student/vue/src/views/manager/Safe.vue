<template>
  <div class="container">
    <div class="centered-div">隐私安全
      <div class="student-div">
        <el-form :model="data.form" label-width="100px" label-position="right" label="安全">
          <el-form-item label="学生账号">
            <el-input v-model="data.form.username" autocomplete="off" disabled/>
          </el-form-item>
          <el-form-item label="学生密码">
            <el-input show-password v-model="data.form.password" autocomplete="off" disabled/>
          </el-form-item>
        </el-form>
      </div>
      <div class="student-div">
        <el-form-item>
          <el-button type="primary" @click="ResetPassword">修改密码</el-button>
        </el-form-item>
      </div>
    </div>
    <el-dialog style="width: 35%;text-align: center" v-model="data.formVisible" title="修改密码">
      <el-form :model="data.formData" ref="passwordForm" label-width="100px" label-position="right">
        <el-form-item label="原密码" prop="oldPassword">
          <el-input v-model="data.oldPassword" placeholder="请输入原密码"></el-input>
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
</template>

<script setup>
import {reactive} from "vue";
import request from "../../utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const data = reactive({
  form: JSON.parse(localStorage.getItem('student-user') || "{}"),
  formData: {},
  formVisible:false,
  oldPassword: '',
  newPassword: '',
  confirmPassword: '',
})

  const ResetPassword = () => {
    data.formVisible = true
    data.formData.username = data.form.username
  }

  const SavePassword = () => {
      request.post('/students/check-password', {username:data.formData.username, oldPassword: data.oldPassword,newPassword: data.newPassword }).then(res => {
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

<style>
.container {
  display: grid;
  place-items: center;
}
.centered-div {
  width: 450px;
  background-color: #fff;
  border-radius: 5px;
  padding: 10px 10px 10px 10px;
  box-shadow: 0 0 10px rgba(0,0,0,.1);
  margin: 30px 10px 10px 10px;
  text-align: center;
}
.student-div{
  margin: 20px 10px 10px 10px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>