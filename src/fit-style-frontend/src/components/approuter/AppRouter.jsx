import React from 'react';
import {Switch, Route, BrowserRouter} from "react-router-dom";

import {NavbarContainer} from "../navbar/NavbarContainer";
import {LoginContainer} from "../../pages/login/LoginContainer";

import {routes} from "../../pages/routes/routes";
import {URL_LOGIN} from "../../config/consts/urlsPages";

import {Background} from "../background/Background";
import {useAuth} from "../../packages/auth/useAuth";


const AppRouter = () => {
    const {isAuth, roles} = useAuth();

    return (
    <BrowserRouter>
          {isAuth &&
              <div>
                  <Background/>
                  <NavbarContainer/>
              </div>
          }
          <Switch>
              {isAuth
                ? routes.map(({path, Component, reqRole}) =>
                      !!(roles.indexOf(reqRole) + 1) ? <Route key={path} path={path} component={Component}/> : null)
                :
                <Route path={[URL_LOGIN, '/']} render={ props => <LoginContainer {...props}/>}/>
              }
          </Switch>
      </BrowserRouter>
    );
};

export default AppRouter;