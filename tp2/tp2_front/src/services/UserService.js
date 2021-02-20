import axios from 'axios';

export default {
    saveUser(user){
        return axios.post(`http://localhost:8081/user`,user)
    }
}