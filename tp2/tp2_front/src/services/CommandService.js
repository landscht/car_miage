import axios from "axios";
import authHeader from "@/services/AuthHeader";

export default {

    saveCommand(command) {
        return axios.post('http://localhost:8081/command', command, {
            headers: authHeader()
        })
    },

    getCommands() {
        return axios.get('http://localhost:8081/command', {
            headers: authHeader()
        })
    }

}