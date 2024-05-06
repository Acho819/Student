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
        <el-button type="primary" style="margin-bottom: 10px" @click="courseXingz">新增</el-button>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="52"/>
          <el-table-column prop="name" label="课程名称"/>
          <el-table-column prop="no" label="课程编号"/>
          <el-table-column prop="descr" label="课程描述"/>
          <el-table-column prop="times" label="课时"/>
          <el-table-column prop="teacher" label="任课老师"/>
          <el-table-column label="操作" width="180">
            <template #default="scope">
              <el-button type="primary" @click="courseEdit(scope.row)">编辑</el-button>
              <el-button type="danger" @click="del(scope.row)">删除</el-button>
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
    <el-dialog style="width: 35%;text-align: center" v-model="data.formVisible" title="课程信息">
      <el-form :model="data.form" label-width="100px" label-position="right">
        <el-form-item label="课程名称">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课程编号">
          <el-input v-model="data.form.no" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课程描述">
          <el-input v-model="data.form.descr" autocomplete="off" />
        </el-form-item>
        <el-form-item label="课时">
          <el-input v-model="data.form.times" autocomplete="off" />
        </el-form-item>
        <el-form-item label="任课老师">
          <el-input v-model="data.form.teacher" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
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
  name:'',
  no:'',
  teacher:'',
  tableData:[],
  total:0,
  pageSize:5,         //每个页面的个数
  pageNum:1,           //页码数
  formVisible:false,
  form:{}
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

const coursechange = (pageNum) => {
  load()
}             //分页重新调用方法

const reset = () => {
  data.name='',
  data.no='',
  data.teacher='',
  load()
}           //重置方法

const courseXingz = () => {
  data.form = {}
  data.formVisible = true
}

const save = () => {
  request.request({
    url:data.form.id ? '/course/update' : '/course/add',
    method:data.form.id ? 'PUT' : 'POST',       //判断是否有id有则新增
    data:data.form
  }).then(res =>{
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

const courseEdit = (row) => {
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true;
}

const del = (row) => {
  const courseId = row.id; // 获取课程的id
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', { type: 'warning' }).then(res => {
    request.delete(`/course/delete/${courseId}`) // 发送删除请求到后端
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
</script>