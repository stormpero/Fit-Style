import React, { Component } from "react";

import LStorageUser from "../../services/localstorage/LStorageUser";
import UserApi from "../../services/api/user/UserApi";

export default class UserContent extends Component {
  state = {
    content: "",
    email: ""
  };

  componentDidMount() {
    const currentUser = LStorageUser.getUser();
    UserApi.getUserBoard().then(
      response => {

        this.setState({
          content: response.data,
          email: currentUser.email
        });
      },
      error => {
        let errorMsg =  error.response?.data?.message || error.message;
        this.setState({
          content: errorMsg
        });
      }
    );
  }

  render() {
    const email = this.state.email;

    return (
        <div>
          <ul className="circles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
      <div className="container">
        <header className="jumbotron">
          <h3 className="text-white">{this.state.content + email + "!"}</h3>
        </header>
      </div>
        </div>
    );
  }
}
