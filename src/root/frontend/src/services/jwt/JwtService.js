import LStorageUser from "../LStorageUser";

class JwtService {

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