import React, { Component } from "react";

import LStorageUser from "../../services/LStorageUser";
import UserService from "../../services/api/UserService";
import DateFormat from "../../services/utils/DateFormat";
import Profile from "./Profile";

export default class ProfileContainer extends Component {
    state = {
        userReady: false,
        currentUser: { username: "" },
        userInfo: null,
        img: null
    }

    componentDidMount() {
        const currentUser = LStorageUser.getUser();
        Promise.allSettled([UserService.getProfileInfo(), UserService.getProfileImg()]).then(
            response => {
                const [userInfo, img] = response.map(element => element?.status === "fulfilled" ? element?.value.data :null);

                userInfo.id = ('000000' + currentUser.id).slice(Math.log(Number(currentUser.id)) * Math.LOG10E + 1 | 0);
                userInfo.birthdate = DateFormat.convertDataToNormalData(userInfo.birthdate);
                this.setState({
                    userInfo: userInfo,
                    currentUser: currentUser,
                    img:  img ? URL.createObjectURL(img) : null,
                    userReady: true
                });
            }
        )
    }

    render() {
        const { userInfo, img } = this.state;
        return ((this.state.userReady) ? <Profile userInfo={userInfo} img={img}/> : null);
    }
}