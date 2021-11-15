import React, { Component } from "react";

import isEmpty from "validator/es/lib/isEmpty";
import Login from "./Login";
import AuthService from "../../services/AuthService";

export default class LoginContainer extends Component {

  state = {
    userInfo: {
      email: "",
      password: "",
    },
    isLoading: false,
    errorMsg: ""
  }

  handleInputChange = (event) => {
    const {name, value} = event.target;

    this.setState({
      userInfo: {
        ...this.state.userInfo,
        [name]: value
      }
    });
  }

  handleLogin = (event) => {
    event.preventDefault();
    if (isEmpty(this.state.userInfo.email) || isEmpty(this.state.userInfo.password)) {
      const errorMsg = "Заполните поля";

      this.setState({
        errorMsg: errorMsg,
        isLoading: false,
      });
      return;
    }

    this.setState({
      isLoading: true
    });
    
    //TODO: Проверить данные на ошибки
    AuthService.login(this.state.userInfo).then(
      () => {
        //this.props.history.push("/user");
        //window.location.reload();
        this.setState({
          isLoading: false,
        });
      }).catch((error)=> {
        let errorMsg =  error.response?.data?.message || error.message;

        errorMsg = errorMsg === "Bad credentials" ? "Неверные данные" : "Заполните поля"; //TODO: Проверка на ошибки
       
        this.setState({
          errorMsg: errorMsg,
          isLoading: false,
        });
      });
  }

  render() {
    return (
    <Login
      handleFunc={{
        login: this.handleLogin,
        input: this.handleInputChange,
      }}
      value={this.state.userInfo}
      error={this.state.errorMsg}
    /> );
  }
}