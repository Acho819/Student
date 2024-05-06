<template>
  <div class="container">
    <div class="centered-div">学生信息
      <div class="student-div">
      <el-form :model="data.form" label-width="100px" label-position="right" label="学生信息">
        <el-form-item label="学生账号">
          <el-input v-model="data.form.username" autocomplete="off" disabled/>
        </el-form-item>
        <el-form-item label="学生密码">
          <el-input show-password v-model="data.form.password" autocomplete="off" disabled/>
        </el-form-item>
        <el-form-item label="学生姓名">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="学生电话">
          <el-input v-model="data.form.phonenumber" autocomplete="off" />
        </el-form-item>
        <el-form-item label="学生邮箱">
          <el-input v-model="data.form.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="学生性别">
          <el-radio-group v-model="data.form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学生生日">
          <el-date-picker v-model="data.form.bith" type="date" placeholder="选择生日" style="width: 100%"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="update">保  存</el-button>
        </el-form-item>
      </el-form>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import request from "../../utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const userDataString = localStorage.getItem('student-user'); // 获取本地存储中的用户信息字符串
const userData = JSON.parse(userDataString || "{}"); // 将字符串解析为对象，如果为空则返回一个空对象
const username = userData.username; // 获取用户名

  const data = reactive({
    form: {}
  })
const update = () => {
  request.put('/student/update', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success("操作成功")
      fetchStudentInfo()            //更新信息后重新获取学生信息
      router.push('/home')
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const fetchStudentInfo = () => {
  const userDataString = localStorage.getItem('student-user'); // 获取本地存储中的用户信息字符串
  const userData = JSON.parse(userDataString || "{}"); // 将字符串解析为对象，如果为空则返回一个空对象
  const username = userData.username; // 获取用户名
  // 如果用户名有效，则向后端发送请求，获取最新的学生信息
  if (username) {
    request.get(`/student/info/${username}`).then(res => {
      if (res.code === '200') {
        // 更新前端表单数据
        data.form = res.data
      } else {
        ElMessage.error(res.msg)
      }
    })
  } else {
    console.error('用户名无效，无法发送请求');
  }
}
// 初始化时获取学生信息
fetchStudentInfo();
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