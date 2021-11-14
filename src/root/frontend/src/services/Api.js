import axios from 'axios';
import JwtService from "./jwt/JwtService";
import LStorageUser from "./LStorageUser";

const instance = axios.create({
    baseURL: "http://localhost:8080/api"
});

instance.interceptors.request.use(
    (config) => {
        const accessToken = JwtService.getAccessToken();
        //alert(accessToken)
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
    async error => {
        const config = error.config;
        if (config.url !== "auth/signin" && error.response) {
            if (error.response.status === 401 && !config._retry) {
                config._retry = true;

                try {
                    instance.post("/auth/refreshtoken", {
                        refreshToken: JwtService.getRefreshToken()
                    })
                    .then(response => {
                        const {accessToken, refreshToken} = response.data;
                        JwtService.updateAccessToken(accessToken);
                        JwtService.updateRefreshToken(refreshToken);
                    })
                    .catch((_error) => {
                        console.error(_error);
                        if (_error.response.status === 403 ) { //TODO: Статус ошибки, проверка на refresh token expired
                            LStorageUser.remove();
                            window.location.reload();
                        }
                    });

                    return instance(config);
                } catch (err) {
                    return Promise.reject(err);
                }
            }
            return Promise.reject(error);
        }
    }
);

export default instance;