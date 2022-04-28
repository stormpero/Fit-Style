import AuthContext from "./authContext";
import React, {useEffect, useState} from "react";
import {auth} from "../api";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../config/consts/ToastPosition";
import LStorage from "../../services/jwt/Jwt";

export function AuthProvider({children}) {
  const [isAuth, setIsAuth] = useState(false);
  const [user, setUser] = useState(null);
  const [roles, setRoles] = useState([]);

  useEffect(() => {
    if (LStorage.isTokenExists()) {
       auth.getUserData()
        .then(response => {
          setUser(response.data);
          setRoles(response.data.roles);
        })
        .catch(error => {
          console.log(error.response);
        }).finally(() => setIsAuth(true));
    }
  }, [])

  const login = ({email, password}) => {
    auth.login({email, password})
      .then(response => {
          const userData = response.data;
          setUser(userData);
          setRoles(userData.roles)
          LStorage.setToken(userData.token);
          setIsAuth(true);
          ToastMessages.success("Добро пожаловать!", TOP_RIGHT);
      })
      .catch(error => {
          console.log(error);
          if (error?.response?.data?.message === "Bad credentials") {
            ToastMessages.error("Неверные данные");
          } else {
            ToastMessages.defaultError();
          }
      });
  }

  const logout = () => {
    auth.logout().then(
      () => {
        ToastMessages.success("До встречи!", TOP_RIGHT);
      },
      error => {
        console.error(error)
        ToastMessages.defaultError();
      }).finally(() => {
        LStorage.removeToken();
        setIsAuth(false);
      });
  }

  return <AuthContext.Provider value={{
    isAuth,
    user,
    roles,
    login,
    logout
  }}>
    {children}
  </AuthContext.Provider>
}