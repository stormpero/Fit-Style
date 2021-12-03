import api from "./Api";
import {URL_AUTH} from "../../config/consts/urlsApi";
import LStorageUser from "../localstorage/LStorageUser";

class LoginApi {
    login(userInfo) {
        return api
            .post(URL_AUTH, userInfo, {
                withCredentials: true,
            })
            .then(response => {
                if (response.data.token) {
                    LStorageUser.setUser(response.data);
                }
                return response.data;
            })
            .catch(error => {
                return Promise.reject(error)
            });
    }
}

export default new LoginApi();