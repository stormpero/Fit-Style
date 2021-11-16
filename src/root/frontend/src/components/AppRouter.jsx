import React, {useEffect, useState} from 'react';
import {Switch, Route, Redirect} from "react-router-dom";
import LStorageUser from "../services/LStorageUser";
import NavbarContainer from "./navbar/NavbarContainer";
import {routes} from "../pages/routes/routes";
import LoginContainer from "../pages/login/LoginContainer";
import {URL_LOGIN, URL_NEWS} from "../services/utils/consts/urlsPages";

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
                {!isAuth && <Route path={URL_LOGIN} render={props => <LoginContainer
                        Auth={{isAuth: isAuth, setIsAuth: setIsAuth}} {...props}/>}/>
                }
                <Redirect to={isAuth ? URL_NEWS : URL_LOGIN} />
            </Switch>
        </div>
    );
};

export default AppRouter;