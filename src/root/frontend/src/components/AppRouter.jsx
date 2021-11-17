import React, {useEffect, useState} from 'react';
import {Switch, Route} from "react-router-dom";

import NavbarContainer from "./navbar/NavbarContainer";
import LoginContainer from "../pages/login/LoginContainer";

import {routes} from "../pages/routes/routes";
import {URL_LOGIN} from "../services/utils/consts/urlsPages";

import LStorageUser from "../services/LStorageUser";

const AppRouter = () => {

    const [isAuth, setIsAuth] = useState(false);
    //ROLES

    useEffect( () => {
        setIsAuth(LStorageUser.isExist())
    }, [isAuth])

    return (
        <div>
            { isAuth && <NavbarContainer /> }
            <Switch>
                {isAuth && routes.map(({path, Component, role})=>
                    <Route key={path} path={path} component={Component} />
                )}
                {!isAuth && <Route path={[URL_LOGIN, '/']} render={ props =>
                    <LoginContainer Auth={{isAuth: isAuth, setIsAuth: setIsAuth}} {...props}/>}/>
                }


            </Switch>
        </div>
    );
};

export default AppRouter;