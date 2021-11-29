import React, { Component } from "react";
import UserService from "../../services/api/user/UserService";
import LStorageUser from "../../services/localstorage/LStorageUser";

export default class UserContent extends Component {
  state = {
    content: "",
    email: ""
  };

  componentDidMount() {
    const currentUser = LStorageUser.getUser();
    UserService.getUserBoard().then(
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
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content + email + "!"}</h3>
        </header>
      </div>
    );
  }
}
