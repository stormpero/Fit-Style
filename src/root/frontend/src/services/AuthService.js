import api from "./Api";
import LStorageUser from "./LStorageUser";
import {URL_AUTH, URL_REGISTER} from "./utils/consts/urlsApi";

class AuthService {
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

    register(userInfo) {
        return api.post(URL_REGISTER, userInfo);
    }
}

export default new AuthService();
