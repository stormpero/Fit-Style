import api from "./Api";

class UserService {

  getUserBoard() {
    return api.get('test/user');
  }

  getProfileInfo(id) {
    return api.get('profile', { params: { id: id} });
  }

  getRoles(id) {
    return api.get('perm/roles', { params: { id: id} });
  }
}

export default new UserService();
