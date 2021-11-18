import api from "./Api";

class UserService {

  getUserBoard() {
    return api.get('test/user');
  }

  getProfileInfo(id) {
    return api.get('profile');
  }

  getRoles() {
    return api.get('permission/roles');
  }

  getNews(pageNumber) {
    return api.get(`news/${pageNumber}`);
  }
}

export default new UserService();
