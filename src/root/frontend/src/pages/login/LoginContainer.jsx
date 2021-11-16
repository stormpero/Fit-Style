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
    console.log(this.state.userInfo)
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
        this.props.Auth.setIsAuth(true);
        this.props.history.push("/user");
        this.setState({
          isLoading: false,
        });
      }).catch((error)=> {
        let errorMsg =  error.response?.data?.message || error.message;
        console.log(error.message)
        errorMsg = errorMsg === "Bad credentials" ? "Неверные данные" : "Заполните поля"; //TODO: Проверка на ошибки
       
        this.setState({
          errorMsg: errorMsg,
          isLoading: false,
        });
      });
  }

  render() {
    console.log(this.props)
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