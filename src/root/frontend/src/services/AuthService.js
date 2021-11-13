import api from "./Api";
import LStorageUser from "./LStorageUser";

class AuthService {
    login(userInfo) {
        return api
            .post('auth/signin', userInfo)
            .then(response => {
                console.log(response.data)
                if (response.data.accessToken) {
                    LStorageUser.setUser(response.data);
                }
                return response.data;
            }).catch(error => {
                console.error(error)
            });
    }

    logout() {
        LStorageUser.remove();
    }

    register(userInfo) {
        return api
            .post('auth/signup', userInfo);
    }
}

export default new AuthService();
