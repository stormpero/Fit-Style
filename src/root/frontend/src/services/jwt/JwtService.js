import LStorageUser from "../LStorageUser";

class JwtService {
    parseJwt(token) {
        try {
            return JSON.parse(atob(token.split('.')[1]));
        } catch (e) {
            return null;
        }
    };

    getAccessToken() {
        const user = LStorageUser.getUser();
        return user ? user?.token : null;
    }

    updateAccessToken(token) {
        const user = LStorageUser.getUser();
        user.token = token;
        LStorageUser.setUser(user);
    }
}

export default new JwtService()