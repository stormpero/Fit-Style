import React from 'react';
import {Switch, Route} from "react-router-dom";

import {LoginContainer} from "../pages/login/LoginContainer";

import {PrivateRoute} from "../components/privateroute/PrivateRoute";
import {NavbarContainer} from "../components/navbar/NavbarContainer";
import {Background} from "../components/backgrounds/appbackground/Background";
import {useAuth} from "../packages/auth/useAuth";

import {routes} from "../pages/routes/routes";
import {URL_LOGIN} from "../config/consts/urlsPages";


const AppRouter = () => {
  const {isAuth} = useAuth();

  if (!isAuth) {
    return <Route path={[URL_LOGIN, '/']} render={props => <LoginContainer {...props}/>}/>;
  }

  return (
    <Background>
      <NavbarContainer/>
      <Switch>
        {routes.map(({
            path,
            Component,
            reqRole
          }) => <PrivateRoute key={path} path={path} reqRole={reqRole} component={Component}/>
        )}
      </Switch>
    </Background>
  );
};

export default AppRouter;

