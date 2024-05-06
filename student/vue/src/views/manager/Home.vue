<template>
  <div>

    <div class="card" style="line-height: 30px">
      <div>欢迎您,<a href="/person" style="color: lightskyblue">{{user.name}}</a> 祝您今天过得开心！</div>
    </div>

    <div style="margin-top: 90px">
      <el-carousel :interval="4000" type="card" height="300px">
        <el-carousel-item v-for="(item,index) in carouselItems" :key="index">
          <img :src="item.image" alt="item.title" style="height: 300px">
        </el-carousel-item>
      </el-carousel>
    </div>

  </div>
</template>

<style scoped>

</style>

<script setup>
  import request from "@/utils/request";
  const user = JSON.parse(localStorage.getItem('student-user') || '{}')

  const carouselItems= [
    { title: 'Item 1', image: '/public/Home-Card/JXHJXY.png' },
    { title: 'Item 2', image: "/public/Home-Card/student.png" },
    { title: 'Item 3', image: "/public/Home-Card/message.png" },
    { title: 'Item 4', image: "/public/Home-Card/mail.png" },
    { title: 'Item 5', image: "/public/Home-Card/teacher.png" },
  ]

</script>

