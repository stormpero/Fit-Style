import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import JwtService from "../services/jwt/JwtService";

class AuthVerify extends Component {
    constructor(props) {
        super(props);

        props.history.listen(() => {
            const user = JSON.parse(localStorage.getItem("user"));

            if (user) {
                const decodedJwt = JwtService.parseJwt(user.accessToken);
                if (decodedJwt.exp * 1000 < Date.now()) {
                    props.logOut();
                }
            }
        });
    }

    render() {
        return <div></div>;
    }
}

export default withRouter(AuthVerify);
