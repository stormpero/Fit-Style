import React from "react";
import {Route, Switch, useLocation} from "react-router-dom";
import PrivateRoute from "./PrivateRoute";
import LoginContainer from "../login/LoginContainer";
import RegisterContainer from "../register/RegisterContainer";
import ProfileContainer from "../profile/ProfileContainer";
import UserContent from "../../components/UserContent";
import NewsBoard from "../news/NewsBoard";
import {TransitionGroup, CSSTransition} from "react-transition-group";
//import "./Routes.css"

const Routes = () => {
   // const location = useLocation()
    return (
        //<TransitionGroup>
            //<CSSTransition
              // timeout={100}
              // classNames='page'
              // key={location.key}
           // >
                <Switch>
                    <Route exact path={["/", "/login"]} component={LoginContainer} />
                    <PrivateRoute role={"ROLE_MODERATOR"} path="/register" component={RegisterContainer} />
                    <PrivateRoute role={"ROLE_USER"} path="/profile" component={ProfileContainer} />
                    <PrivateRoute role={"ROLE_USER"} path="/user" component={UserContent} />
                    <PrivateRoute role={"ROLE_USER"} path="/news" component={NewsBoard} />
                </Switch>
           // </CSSTransition>
        //</TransitionGroup>
    )
}

export { Routes };