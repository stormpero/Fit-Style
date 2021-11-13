import React from "react";
import {Route, Switch} from "react-router-dom";

import PrivateRoute from "./PrivateRoute";

import LoginContainer from "../login/LoginContainer";
import RegisterContainer from "../register/RegisterContainer";
import ProfileContainer from "../profile/ProfileContainer";
import UserContent from "../../components/UserContent";
import NewsBoard from "../news/NewsBoard";

const Routes = () => {
    return (
        <Switch>
            <Route exact path={["/", "/login"]} component={LoginContainer} />
            <PrivateRoute role={"ROLE_MODERATOR"} path="/register" component={RegisterContainer} />
            <PrivateRoute role={"ROLE_USER"} path="/profile" component={ProfileContainer} />
            <PrivateRoute role={"ROLE_USER"} path="/user" component={UserContent} />
            <PrivateRoute role={"ROLE_USER"} path="/news" component={NewsBoard} />
        </Switch>
    )
}

export { Routes };