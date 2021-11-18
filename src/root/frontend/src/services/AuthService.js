import api from "./Api";
import LStorageUser from "./LStorageUser";

class AuthService {
    login(userInfo) {
        return api
            .post('auth/signin', userInfo, {
                withCredentials: true,
            })
            .then(response => {
                if (response.data.token) {
                    LStorageUser.setUser(response.data);
                }
                return response.data;
            })
            .catch(error => {
                return Promise.reject(error)
            });
    }

    logout() {
        // const userId = LStorageUser.getId();
        // api.post('auth/logout', {userId: userId})
        //    .then(response => {
        //         console.log(response)
        //         LStorageUser.remove();
        //    })
        //    .catch(error =>{
        //         console.error(error)
        //         LStorageUser.remove();
        //    })
        // window.location.reload();
        LStorageUser.remove();
    }

    register(userInfo) {
        return api.post('auth/signup', userInfo);
    }
}

export default new AuthService();
