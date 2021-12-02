import api from "../config/Api";
import {
    URL_ROLES,
    URL_SUBSCRIPTIONTYPE,
    URL_USERTEST
} from "../../../config/consts/urlsApi";

class UserApi {
    getUserBoard() {
        return api.get(URL_USERTEST);
    }
    getRoles() {
        return api.get(URL_ROLES);
    }
    getSubscriptionType() {
        return api.get(URL_SUBSCRIPTIONTYPE);
    }
}

export default new UserApi();
