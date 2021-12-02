import api from "../config/Api";
import {URL_REGISTER} from "../../../config/consts/urlsApi";

class RegisterApi {
    register(userInfo) {
        return api.post(URL_REGISTER, userInfo);
    }
}

export default new RegisterApi();