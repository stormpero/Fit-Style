import LStorageUser from "./LStorageUser";

export default function authHeader() {
  const user =LStorageUser.getUser();

  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken };
  } else {
    return {};
  }
}
