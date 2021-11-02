import axios from 'axios';
import JwtService from "./jwt/JwtService";

const instance = axios.create({
    baseURL: "http://localhost:8080/api"
});

instance.interceptors.request.use(
    (config) => {
        const accessToken = JwtService.getAccessToken();
        if (accessToken) {
            config.headers["Authorization"] = 'Bearer ' + accessToken;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

instance.interceptors.response.use(
    (response) => {
        return response;
    },
    error => {
        return Promise.reject(error);
    }
);

export default instance;