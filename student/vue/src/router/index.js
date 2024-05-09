import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Manager',
      component: () => import('@/views/Manager.vue'),
      redirect: '/login',
      children: [
        { path: 'home', name: 'Home', component: () => import('@/views/manager/Home.vue')},
        { path: 'course', name: 'Course', component: () => import('@/views/manager/Course.vue')},
        { path: 'person', name: 'Person', component: () => import('@/views/manager/Person.vue')},
        { path: 'student', name: 'Student', component: () => import('@/views/manager/Student.vue')},
        { path: 'safe', name: 'Safe', component: () => import('@/views/manager/Safe.vue')},
        { path: 'Student-Course', name: 'Student-Course', component: () => import('@/views/manager/Student-Course.vue')},
        { path: 'Students-Course-List', name: 'Students-Course-List', component: () => import('@/views/manager/Students-Course-List.vue')},
      ]
    },
    {path: '/Login', name: 'Login', component: () => import('@/views/Login.vue')},
    {path: '/enroll', name: 'enroll', component: () => import('@/views/enroll.vue')},
    {path: '/back', name: 'back', component: () => import('@/views/back.vue')},
  ]
})

export default router
