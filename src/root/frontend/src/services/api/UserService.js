import api from "./Api";
import {URL_PROFILE, URL_ROLES, URL_USERTEST} from "../utils/consts/urlsApi";

class UserService {
  getUserBoard() {
    return api.get(URL_USERTEST);
  }

  getProfileInfo(id) {
    return api.get(URL_PROFILE);
  }

  getRoles() {
    return api.get(URL_ROLES);
  }
}

export default new UserService();
