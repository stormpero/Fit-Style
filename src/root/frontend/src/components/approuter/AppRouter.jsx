import React, {useEffect, useState} from 'react';
import {Switch, Route} from "react-router-dom";

import {NavbarContainer} from "../navbar/NavbarContainer";
import LoginContainer from "../../pages/login/LoginContainer";

import {routes} from "../../pages/routes/routes";
import {URL_LOGIN} from "../../config/consts/urlsPages";

import LStorageUser from "../../services/localstorage/LStorageUser";
import UserService from "../../services/api/user/UserService";

const AppRouter = () => {
    const [isAuth, setIsAuth] = useState(false);
    const [roles, setRoles] = useState([]);
    const [isLoad, setIsLoad] = useState(false)

    useEffect( () => {
        setIsAuth(LStorageUser.isExist())
        if (isAuth) {
            UserService.getRoles()
            .then(roles => {
                setRoles(roles.data?.roles.map(res => res.name));
            }).finally(() => setIsLoad(true));
        }

    }, [isAuth])

    return (
        <div>
            { isAuth &&  <NavbarContainer setIsAuth={setIsAuth}/> }
            <Switch>
                {isAuth && isLoad && routes.map(({path, Component, reqRole}) =>
                        !!(roles.indexOf(reqRole) + 1) ? <Route key={path} path={path} component={Component}/> : null
                )}
                {!isAuth && <Route path={[URL_LOGIN, '/']} render={ props =>
                    <LoginContainer setIsAuth={setIsAuth} {...props}/>}/>
                }
            </Switch>
        </div>
    );
};

export default AppRouter;