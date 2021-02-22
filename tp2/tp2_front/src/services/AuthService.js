import axios from 'axios';
import router from "@/router/router";

const API_URL = 'http://localhost:8081/auth/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'signin', {
                email: user.email,
                password: user.password
            });
    }

    logout() {
        localStorage.removeItem('access_token');
        router.push('/signin');
    }

    register(user) {
        return axios.post(API_URL + 'signup', user);
    }

    isLoggin() {
        const token = localStorage.getItem('access_token');
        return !!token;
    }
}

export default new AuthService();