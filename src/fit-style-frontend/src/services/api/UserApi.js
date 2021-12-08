import api from "./Api";
import {
    URL_ALL_USERS,
    URL_ROLES,
    URL_SUBSCRIPTIONTYPE
} from "../../config/consts/urlsApi";

class UserApi {
    getAllUsers() {
        return api.get(URL_ALL_USERS);
    }
    getRoles() {
        return api.get(URL_ROLES);
    }
    getSubscriptionType() {
        return api.get(URL_SUBSCRIPTIONTYPE);
    }

}

export default new UserApi();
