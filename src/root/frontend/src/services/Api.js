import axios from 'axios';
import JwtService from "./jwt/JwtService";
import LStorageUser from "./LStorageUser";

const instance = axios.create({
    baseURL: "http://localhost:8080/api",
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
    async error => {
        const config = error.config;
        console.log('error.response', error.response)
        if (config.url !== "auth/signin" && error.response) {
            if (error?.response?.status === 401 && !config._retry) {
                config._retry = true;

                try {
                    instance.post("/auth/refreshtoken", {}, {
                        withCredentials: true,
                        headers: {
                            'Access-Control-Allow-Origin': 'http://localhost:8080',
                            'Access-Control-Allow-Credentials': true,
                        },
                    })
                    .then(response => {
                        console.log('response.data', response.data)
                        const {accessToken} = response.data;
                        JwtService.updateAccessToken(accessToken);
                    })
                    .catch((_error) => {
                        console.error(_error);
                        if (_error.response.status === 403 ) {
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