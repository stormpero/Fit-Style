import axios from 'axios';
import JwtService from "./jwt/JwtService";

const API_URL = 'http://localhost:8080/api/test/';

class UserService {

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: JwtService.getAuthHeader() });
  }

  getProfileInfo(id) {
    return axios.get('http://localhost:8080/api/profile', { headers: JwtService.getAuthHeader(), params: { id: id} })
  }

  getRoles(id) {
    return axios.get('http://localhost:8080/api/perm/roles', { headers: JwtService.getAuthHeader(), params: { id: id} })
  }
}

export default new UserService();
