import React, { Component } from "react";

import LStorageUser from "../../services/LStorageUser";
import UserService from "../../services/UserService";
import DateFormat from "../../services/utils/DateFormat";
import Profile from "./Profile";

export default class ProfileContainer extends Component {
  state = {
    userReady: false,
    currentUser: { username: "" },
    userInfo: null
  }
  
  componentDidMount() {
    const currentUser = LStorageUser.getUser();
    UserService.getProfileInfo(currentUser.id).then(
        response => {
          const userInfo = response.data;
          userInfo.id = ('000000' + currentUser.id).slice(Math.log(Number(currentUser.id)) * Math.LOG10E + 1 | 0);
          userInfo.birthdate = DateFormat.convertDataToNormalData(userInfo.birthdate);
          this.setState({
            userInfo: userInfo,
            currentUser: currentUser,
            userReady: true
          });
        }
    );
  }

  render() {
    const { userInfo } = this.state;
    return ((this.state.userReady) ? <Profile userInfo={userInfo}/> : null);
  }
}