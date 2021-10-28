import axios from 'axios';
import JwtService from "./jwt/JwtService";

const API_URL = 'http://localhost:8080/api/test/';

class UserService {

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: JwtService.getAuthHeader() });
  }

  getProfileInfo(getId) {
    return axios.get('http://localhost:8080/api/profile', { headers: JwtService.getAuthHeader(), params: { id: getId} })
  }

}

export default new UserService();
