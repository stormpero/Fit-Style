import {
  setItem,
  getItem,
  removeItem
} from "../storage";

const setToken = (token) => {
  setItem("token", token);
}

const getToken = () => {
  return getItem("token");
}

const removeToken = () => {
  removeItem("token")
}

export {
  setToken,
  getToken,
  removeToken
}