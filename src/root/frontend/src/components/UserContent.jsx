import React, { Component } from "react";

import UserService from "../services/userService";
import EventBus from "../services/EventBus";

export default class UserContent extends Component {
  state = {
    content: ""
  };

  componentDidMount() {
    UserService.getUserBoard().then(
      response => {
        this.setState({
          content: response.data
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
    console.log(this.props.location)
    const username = this.props.location.state.username;

    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content + username + "!"}</h3>
        </header>
      </div>
    );
  }
}
