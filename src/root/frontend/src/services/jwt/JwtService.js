import LStorageUser from "../LStorageUser";

class JwtService {

    getAuthHeader() {
        const user = LStorageUser.getUser();

        if (user && user.accessToken) {
            return { Authorization: 'Bearer ' + user.accessToken };
        } else {
            return {};
        }
    }
}

export default new JwtService()