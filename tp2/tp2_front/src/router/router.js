import Vue from 'vue'
import VueRouter from 'vue-router'
import ProductScreen from "@/screens/ProductScreen";
import HomeScreen from "@/screens/HomeScreen";
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeScreen
    },
    {
        path: '/products',
        name: 'products',
        component: ProductScreen
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router;