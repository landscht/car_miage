import axios from "axios";

export default {

    getAllCommand(page) {
        return axios.get("http://localhost:8081/command", {params: {page: page}});
    }

}