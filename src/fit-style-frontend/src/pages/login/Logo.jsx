import React from 'react';
import logo from "../../assets/logo.png";
import {Link} from "react-router-dom";

export const Logo = () => {
    return (
        <div className="d-flex justify-content-center">
            <Link to={"/"} className="navbar-brand ">
                <img className="logo" src={logo} alt="Fit-Style"/>
            </Link>
        </div>
    );
};
