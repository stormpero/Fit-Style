import React, {useEffect, useState} from 'react';
import {Switch, Route, Redirect} from "react-router-dom";
import LStorageUser from "../services/LStorageUser";
import Navbar from "./navbar/Navbar";
import {loginRoute, routes} from "../pages/routes/routesConst";
import LoginContainer from "../pages/login/LoginContainer";

const AppRouter = () => {

    const [isAuth, setIsAuth] = useState(false);
    //ROLES

    useEffect( () => {
        setIsAuth(LStorageUser.isExist())
        console.log('useEffect, isAuth: ', isAuth);
    }, [isAuth])

    return (
        <div>
            { isAuth && <Navbar /> }
            <Switch>
                {isAuth && routes.map(({path, Component, role})=>
                    <Route key={path} path={path} component={Component} />
                )}
                {!isAuth && <Route path={loginRoute.path} render={props => <LoginContainer
                        Auth={{isAuth: isAuth, setIsAuth: setIsAuth}} {...props}/>}/>
                }
                <Redirect to={isAuth ? '/news' : '/login'} />
            </Switch>
        </div>
    );
};

export default AppRouter;