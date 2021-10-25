import React, { Component } from "react";
import { withRouter } from "react-router-dom";

import LStorageUser from "../LStorageUser";

export default function requireAuth(сomponent) {

    class AuthComponent extends Component {

        componentWillMount() {
            if (!LStorageUser.isExist()) {
                this.props.history.push("/login");
            }
        }

        render() {
            return LStorageUser.isExist()
                ? <сomponent { ...this.props } />
                : null;
        }
    }

    return withRouter(AuthComponent);
}