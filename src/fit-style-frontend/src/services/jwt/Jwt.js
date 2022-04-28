const getToken = () => {
  return JSON.parse(localStorage.getItem("token"));
}

const setToken = (token) => {
  localStorage.setItem("token", JSON.stringify(token));
}

const removeToken = () => {
  localStorage.removeItem("token");
}

const isTokenExists = () => {
  return localStorage.getItem("token") !== null;
}

const LStorage = {
  getToken,
  setToken,
  removeToken,
  isTokenExists
}

export default LStorage;