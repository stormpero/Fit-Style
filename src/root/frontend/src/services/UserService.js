import axios from 'axios';
import JwtService from "./jwt/JwtService";

const API_ROOT_URL = 'http://localhost:8080/api/';

class UserService {

  getUserBoard() {
    return axios.get(API_ROOT_URL + 'test/user', { headers: JwtService.getAuthHeader() });
  }

  getProfileInfo(id) {
    return axios.get(API_ROOT_URL + 'profile', { headers: JwtService.getAuthHeader(), params: { id: id} })
  }

  getRoles(id) {
    return axios.get(API_ROOT_URL + 'perm/roles', { headers: JwtService.getAuthHeader(), params: { id: id} })
  }
}

export default new UserService();
