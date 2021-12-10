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
            config.headers.Authorization = 'Bearer ' + accessToken;
        }
        return config;
    },
    error => Promise.reject(error)
);

let isAlreadyFetchingAccessToken = false;
let subscribers = [];

instance.interceptors.response.use(
    (response) => {
        return response;
    },
    error => {
        if (isTokenExpiredError(error)) {
            return resetTokenAndReattemptRequest(error)
        }
        return Promise.reject(error)
    }
);

function isTokenExpiredError(error) {
    const {config, response} = error;
    return config?.url !== URL_AUTH && response?.status === 401
}

async function resetTokenAndReattemptRequest(error) {
    try {
        const {response: errorResponse} = error;

        const retryOriginalRequest = new Promise(resolve => {
            addSubscriber(access_token => {
                errorResponse.config.headers.Authorization = 'Bearer ' + access_token;
                resolve(axios(errorResponse.config));
            });
        });

        if (!isAlreadyFetchingAccessToken) {
            isAlreadyFetchingAccessToken = true;
            let response = await instance.get(URL_REFRESHTOKEN, {
                withCredentials: true
            })
            const {accessToken} = response.data;
            JwtService.updateAccessToken(accessToken);
            isAlreadyFetchingAccessToken = false;
            onAccessTokenFetched(accessToken);
        }
        return retryOriginalRequest;
    } catch (err) {
        console.log(err.response)
        if (err.response.data.errorCode === 2) {
            LStorageUser.remove();
            window.location.reload();
        }
        return Promise.reject(err);
    }
}

function onAccessTokenFetched(accessToken) {
    subscribers.forEach(callback => callback(accessToken));
    subscribers = [];
}

function addSubscriber(callback) {
    subscribers.push(callback);
}

export default instance;

