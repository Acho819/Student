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
      ]
    },
    {path: '/Login', name: 'Login', component: () => import('@/views/Login.vue')},
    {path: '/enroll', name: 'enroll', component: () => import('@/views/enroll.vue')}
  ]
})

export default router
