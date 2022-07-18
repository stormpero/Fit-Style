import React from 'react';
import Navbar from "./Navbar";
import {useAuth} from "../../packages/auth/useAuth";
import {useRole} from "../../customHooks/useRole";

export const NavbarContainer = () => {
    const isModer = useRole("MODERATOR")
    const {logout} = useAuth();
    return <Navbar isModer={isModer} logout={logout}/>;
}
