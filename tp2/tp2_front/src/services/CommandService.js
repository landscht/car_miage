import axios from "axios";
import authHeader from "@/services/AuthHeader";

export default {

    saveCommand(command) {
        return axios.post('http://localhost:8081/command', command, {
            headers: authHeader()
        })
    },

    getAllCommand(page) {
        return axios.get("http://localhost:8081/command", {
            params: {
                page: page
            },
            headers: authHeader()
        });
    },

    getAllCommandByUser() {
        return axios.get("http://localhost:8081/command/user", {
            headers: authHeader()
        });
    }

}