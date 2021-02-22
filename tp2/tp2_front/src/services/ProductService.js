import axios from 'axios';
import authHeader from "@/services/AuthHeader";


export default {
    getAllProducts(page){
        return axios.get(`http://localhost:8081/product`,{
            params: {
                page : page
            },
            headers: authHeader()
        })
    },
    getAllProductsInStock(page){
        return axios.get(`http://localhost:8081/product/inStock`,{
            params: {
                page : page
            },
            headers: authHeader()
        })
    },
    getProductById(id){
        return axios.get(`http://localhost:8081/product/${id}`, {
            headers: authHeader()
        });
    }
}