import axios from 'axios';
import JwtService from "./jwt/JwtService";

const API_URL = 'http://localhost:8080/api/test/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: JwtService.getAuthHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: JwtService.getAuthHeader() });
  }

}

export default new UserService();
