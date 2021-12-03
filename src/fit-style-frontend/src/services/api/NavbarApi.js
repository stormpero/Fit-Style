import api from "./Api";
import {URL_LOGOUT} from "../../config/consts/urlsApi";

class NavbarApi {
    logout() {
        return api.get(URL_LOGOUT);
    }
}

export default new NavbarApi();