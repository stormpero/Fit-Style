import axios from "axios";
import LStorageUser from "./LStorageUser";
import JwtService from "./jwt/JwtService";
const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
    login(username, password) {
        return axios
            .post(API_URL + "signin", {
                username,
                password
            })
            .then(response => {
                if (response.data.accessToken) {
                    LStorageUser.add(response.data);
                }
                return response.data;
            });
    }

    logout() {
        LStorageUser.remove();
    }

    register(username, email, password) {
        return axios.post(API_URL + "signup", {
            username,
            email,
            password
        }, { headers: JwtService.getAuthHeader() });
    }
}

export default new AuthService();