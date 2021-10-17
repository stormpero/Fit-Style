import React, { Component } from "react";

import AuthService from "../services/auth.service";
import isEmpty from "validator/es/lib/isEmpty";

export default class Login extends Component {

  state = {
    username: "",
    password: "",
    isLoading: false,
    errorMsg: ""
  }
  
  handleInputChange = (event) => {
    const {name, value} = event.target;

    this.setState({
      [name]: value
    });
  }

  handleLogin = (event) => {
    event.preventDefault();
    if (isEmpty(this.state.username) || isEmpty(this.state.password)) {
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

    AuthService.login(this.state.username, this.state.password).then(
      () => {
        this.props.history.push("/profile");
        window.location.reload();
        this.setState({
          isLoading: false,
        });
      }).catch((error)=> {
        let errorMsg =  error.response?.data?.message || error.message;
        console.log(error.response.data);
        errorMsg = errorMsg === "Bad credentials" ? "Неверные данные" : "Заполните поля"; //TODO: Проверка на ошибки
       
        this.setState({
          errorMsg: errorMsg,
          isLoading: false,
        });
      });
  }

  render() {
    return (
      <div className="col-md-12">
        <div className="card card-container">
          <h3 className="text-muted">Auth</h3>
          <form>
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <input className="form-control"
                  required
                  name="username"
                  type="text"
                  onChange={this.handleInputChange}
                  value={this.state.username}                  
                  placeholder="Username"
              />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input className="form-control"
                  required
                  name="password"
                  type="password"
                  onChange={this.handleInputChange}
                  value={this.state.password}
                  placeholder="Password"
              />
            </div>
            <br/>
            <div className="form-group d-flex justify-content-between">
              <button className="btn btn-primary btn-block"
                onClick={this.handleLogin}>
                <span>Login</span>
              </button>
            </div>
            {this.state.errorMsg && (
              <div className="form-group">
                <p style={{color: 'red'}}>{this.state.errorMsg}</p>
              </div>
            )              
            }
          </form>
        </div>
      </div>
    );
  }
}