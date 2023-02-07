import AuthContext from "./authContext";
import React, {useEffect, useState} from "react";
import {auth} from "../api";
import ToastMessages from "../../components/toastmessages/ToastMessages";
import {TOP_RIGHT} from "../../config/consts/ToastPosition";
import {token} from "../storage";

export function AuthProvider({children}) {
  const [isAuth, setIsAuth] = useState(false);
  const [user, setUser] = useState(null);
  const [roles, setRoles] = useState([]);

  useEffect(() => {
    if (token.getToken()) {
       auth.getUserData()
        .then(response => {
          setUser(response.data);
          setRoles(response.data.roles);
          setIsAuth(true);
        })
        .catch(error => {
          console.log(error.response);
        });
    }
  }, [])

  const login = ({email, password}) => {
    auth.login({email, password})
      .then(response => {
          const userData = response.data;
          setUser(userData);
          setRoles(userData.roles)
          token.setToken(userData.token)
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
        token.removeToken();
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