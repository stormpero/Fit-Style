import React, { Component } from "react";
import UserService from "../services/UserService";
import EventBus from "../services/EventBus";
import LStorageUser from "../services/LStorageUser";
import {Redirect} from "react-router-dom";

export default class UserContent extends Component {
  state = {
    content: "",
    username: "",
    redirect: null
  };

  componentDidMount() {

    const currentUser = LStorageUser.getUser();

    if (!currentUser) {
      this.setState({ redirect: "/login" });
      return;
    }

    UserService.getUserBoard().then(
      response => {

        this.setState({
          content: response.data,
          username: currentUser.username
        });
      },
      error => {
        let errorMsg =  error.response?.data?.message || error.message;
        this.setState({
          content: errorMsg
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const username = this.state.username;

    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content + username + "!"}</h3>
        </header>
      </div>
    );
  }
}
