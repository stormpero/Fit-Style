import LStorageUser from "../LStorageUser";

export default function hasPermission(role) {
    let cookieRoles = LStorageUser.getRoles();
    return LStorageUser.isExist() && !!(cookieRoles.indexOf(role) + 1);
}


