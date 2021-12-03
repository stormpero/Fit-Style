import api from "./Api";
import {URL_CHANGE_BALANCE, URL_PROFILE_IMG, URL_PROFILE_INFO} from "../../config/consts/urlsApi";

class ProfileApi {
    getProfileInfo() {
        return api.get(URL_PROFILE_INFO);
    }
    getProfileImg() {
        return api.get(URL_PROFILE_IMG, {responseType: 'blob'})
    }
    addBalance(amount) {
        return api.patch(URL_CHANGE_BALANCE, { summary: amount })
    }
}

export default new ProfileApi();