import api from "../config/Api";
import {URL_CHANGE_BALANCE} from "../../../config/consts/urlsApi";

class ProfileApi {
    addBalance(amount) {
        return api.patch(URL_CHANGE_BALANCE, { summary: amount })
    }
}

export default new ProfileApi();