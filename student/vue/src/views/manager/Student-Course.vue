<template>
  <div>
    <div class="card">
      <el-input
          v-model="data.name"
          style="width: 240px"
          placeholder="请输入名称搜索"
          :prefix-icon="Search"
      />
      <el-button type="warning" style="margin-left: 10px" @click="load">搜索</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <div class="card">
      <div>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="52"/>
          <el-table-column prop="name" label="课程名称"/>
          <el-table-column prop="no" label="课程编号"/>
          <el-table-column prop="descr" label="课程描述"/>
          <el-table-column prop="times" label="课时"/>
          <el-table-column prop="teacher" label="任课老师"/>
          <el-table-column label="操作" width="180">
            <template #default="scope">
              <el-button type="primary" @click="SelectCourse(scope.row)">选课</el-button>
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

  </div>
</template>

<script setup>

import {reactive} from "vue";
import { Search } from '@element-plus/icons-vue'
import request from "../../utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  name:'',
  no:'',
  teacher:'',
  tableData:[],
  total:0,
  pageSize:5,         //每个页面的个数
  pageNum:1,           //页码数
  students:JSON.parse(localStorage.getItem('student-user'))
})

const load = () => {
  request.get('/course/selectPage', {
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      name:data.name,
      no:data.no,
      teacher:data.teacher,
    }
  }).then(res=>{
    data.tableData=res.data?.list || []       //调用数据库中的list或者tabledata数组中的数据
    data.total=res.data?.total || 0           //调用数据库中的total或为0
  })
}
load()          //调用方法
const reset = () => {
  data.name='',
      data.no='',
      data.teacher='',
      load()
}           //重置方法

const coursechange = (pageNum) => {
  load()
}             //分页重新调用方法

const SelectCourse = (row) => {
  request.post('/StudentsCourse/add', {students_id:data.students.id,name:row.name,no:row.no,course_id:row.id}).then(res =>{
    if(res.code === '200'){
      ElMessage.success("操作成功")
    }else {
      ElMessage.error(res.msg)
    }
  })
}
</script>