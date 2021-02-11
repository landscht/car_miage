import axios from 'axios';


export default {
    getAllProducts(page){
        return axios.get(`http://localhost:8081/product`,{params: { page : page}})
    },
    getAllProductsInStock(page){
        return axios.get(`http://localhost:8081/product/inStock`,{params: { page : page}})
    }
}