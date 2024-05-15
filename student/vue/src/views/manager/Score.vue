<template>
  <div>
    <div class="card">
      <el-input
          v-model="data.studentsName"
          style="width: 240px"
          placeholder="请输入学生名称搜索"
          :prefix-icon="Search"
      />
      <el-button type="warning" style="margin-left: 10px" @click="load">搜索</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <div class="card">
      <div>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="52"/>
          <el-table-column prop="courseName" label="课程名称"/>
          <el-table-column prop="studentsName" label="学生名称"/>
          <el-table-column prop="score" label="分数"/>
          <el-table-column prop="comment" label="评语"/>
          <el-table-column prop="feedback" label="反馈"/>
          <el-table-column label="操作" width="180">
            <template #default="scope">
              <el-button type="primary" @click="ScoreEdit(scope.row)" v-if="data.user.role === 'ADMIN'">编辑</el-button>
              <el-button type="danger" @click="Del(scope.row.id)" v-if="data.user.role === 'ADMIN'">删除</el-button>
              <el-button type="primary" @click="ScoreEdit(scope.row)" v-if="data.user.role === 'STUDENT'">反馈</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="card">
      <el-pagination v-model:current-page="data.pageNum"
                     v-model:page-size="data.pageSize"
                     @current-change="coursechange"
                     background layout="prev, pager, next"
                     :total="data.total"/>
    </div>
    <el-dialog style="width: 35%;text-align: center" v-model="data.formVisible" title="反馈信息">
      <el-form :model="data.form" label-width="100px" label-position="right">
        <el-form-item label="分数" v-if="data.user.role === 'ADMIN'">
          <el-input v-model="data.form.score" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="评语" v-if="data.user.role === 'ADMIN'">
          <el-input type="textarea" v-model="data.form.comment" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="反馈" v-if="data.user.role === 'STUDENT'">
          <el-input type="textarea" v-model="data.form.feedback" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div style="text-align: center">
          <el-button @click="data.formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>

import {reactive} from "vue";
import { Search } from '@element-plus/icons-vue'
import request from "../../utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  form:{},
  formVisible:false,
  studentsName:'',
  tableData:[],
  total:0,
  pageSize:5,         //每个页面的个数
  pageNum:1,           //页码数
  user:JSON.parse(localStorage.getItem('student-user'))
})

const load = () => {
  let params={
    pageNum:data.pageNum,
    pageSize:data.pageSize,
    studentsName:data.studentsName,
  }
  if(data.user.role === 'STUDENT'){   //如果当前登录的是学生，就查询自己的选课列表
    params.students_id = data.user.id
  }
  request.get('/Score/selectPage', {
    params:params
  }).then(res=>{
    data.tableData=res.data?.list || []       //调用数据库中的list或者tabledata数组中的数据
    data.total=res.data?.total || 0           //调用数据库中的total或为0
  })
}
load()          //调用方法
const reset = () => {
  data.studentsName='',
      load()
}           //重置方法

const coursechange = (pageNum) => {
  load()
}             //分页重新调用方法

const Del = (id) => {
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', { type: 'warning' }).then(res => {
    request.delete('/Score/delete/' +id) // 发送删除请求到后端
        .then(res =>{
          if (res.code === '200') {
            ElMessage.success("删除成功"); // 显示删除成功的提示消息
            load(); // 重新加载课程列表
          } else {
            ElMessage.error(res.msg); // 显示删除失败的提示消息
          }
        })
  })
      .catch(res => {
        console.error("删除失败", res); // 打印错误信息
        ElMessage.error("删除失败，请稍后重试"); // 显示删除失败的提示消息
      });
}

const ScoreEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))   //拷贝数据到表单form
  data.formVisible=true
}

const save = () => {
  request.put('/Score/update',data.form).then(res =>{
    if(res.code === '200'){
      load()        //重新获取数据
      data.formVisible = false      //关闭弹窗
      ElMessage.success("操作成功")       //返回成功信息
    }
    else{
      ElMessage.error(res.msg)        //返回错误信息
    }
  })
}
</script>