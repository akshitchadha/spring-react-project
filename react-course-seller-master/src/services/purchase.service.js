import { BASE_API_URL } from '../common/Constants';
import axios from 'axios';
import { authHeader } from './base.service';


const API_URL = BASE_API_URL + '/gateway/purchase';
const API_URL_SAVE_PURCHASE = API_URL+'/savepurchase';
const API_URL_GET_PURCHASED_COURSES = API_URL+'/getpurchasedcourses';

class PurchaseService {

    savePurchase(purchase) {
        return axios.post(API_URL_SAVE_PURCHASE, purchase, {headers: authHeader()});
    }

    getAllPurchaseItems() {
        return axios.get(API_URL_GET_PURCHASED_COURSES, {headers: authHeader()});
    }
}

export default new PurchaseService();
