import Vue from 'vue'
import VueRouter from 'vue-router'
import ProductScreen from "@/screens/ProductScreen";
import HomeScreen from "@/screens/HomeScreen";
import SigninScreen from "@/screens/SigninScreen";
import SignupScreen from "@/screens/SignupScreen";
import BasketScreen from "@/screens/BasketScreen";
import CommandScreen from "@/screens/CommandScreen";
import AuthService from "@/services/AuthService";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/signin',
    },
    {
        path: '/home',
        name: 'home',
        component: HomeScreen
    },
    {
        path: '/products',
        name: 'products',
        component: ProductScreen
    },
    {
        path: '/signin',
        name: 'signin',
        component: SigninScreen
    },
    {
        path: '/signup',
        name: 'signup',
        component: SignupScreen
    },
    {
        path: '/basket',
        name: 'basket',
        component: BasketScreen
    },
    {
        path: '/commands',
        name: '/commands',
        component: CommandScreen
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    const publicPages = ['/signin', '/signup'];
    console.log(to.path);
    const authRequired = !publicPages.includes(to.path);

    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !AuthService.isLoggin()) {
        next('/signin');
    } else {
        next();}
});

export default router;