import axios from 'axios';
import authHeader from "@/services/AuthHeader";

export default {
    saveUser(user){
        return axios.post(`http://localhost:8081/user`,user)
    },

    loadUser() {
        axios.get('http://localhost:8081/user/me', {
            headers: authHeader()
        }).then(data => {
            console.log(data.data);
            localStorage.setItem('user', JSON.stringify(data.data));
        });
    }
}