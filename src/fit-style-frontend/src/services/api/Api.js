import axios from 'axios';
import JwtService from "../jwt/JwtService";
import LStorageUser from "../localstorage/LStorageUser";
import {URL_AUTH, URL_REFRESHTOKEN, URL_ROOT} from "../../config/consts/urlsApi";

const instance = axios.create({
    baseURL: URL_ROOT,
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

let isAlreadyFetchingAccessToken = false;

instance.interceptors.response.use(
    (response) => {
        return response;
    },
    async error => {
        const config = error.config;
        if (config.url !== URL_AUTH && error?.response?.status === 401 && !config.retry) {
            config.retry = true;

            try {
                if (!isAlreadyFetchingAccessToken) {
                    isAlreadyFetchingAccessToken = true;
                    let response = await instance.get(URL_REFRESHTOKEN, {
                        withCredentials: true
                    })
                    const {accessToken} = response.data;
                    JwtService.updateAccessToken(accessToken);
                    isAlreadyFetchingAccessToken = false;
                }
                return instance(config);
            } catch (_error) {
                if (_error.response.data.errorCode === 2) {
                    LStorageUser.remove();
                    window.location.reload();
                }
                return Promise.reject(_error);
            }
        } else {
            return Promise.reject(error);
        }
    }
);

export default instance;


