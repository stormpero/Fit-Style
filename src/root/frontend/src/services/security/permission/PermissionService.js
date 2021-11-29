import LStorageUser from "../../localstorage/LStorageUser";

class PermissionService {
    hasRole(role) {
        return LStorageUser.getUser()?.roles.includes("ROLE_" + role)
    }
}

export default new PermissionService();