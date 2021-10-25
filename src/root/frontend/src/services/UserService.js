import axios from 'axios';
import JwtService from "./jwt/JwtService";

const API_URL = 'http://localhost:8080/api/test/';

class UserService {

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: JwtService.getAuthHeader() });
  }

  getProfileInfo() {
    return axios.get(API_URL + 'profile', { headers: JwtService.getAuthHeader() })
  }

}

export default new UserService();
