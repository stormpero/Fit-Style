import React, { Component } from "react";

import AuthService from "../../services/AuthService";
import isEmpty from "validator/es/lib/isEmpty";
import LStorageUser from "../../services/LStorageUser";
import {Redirect} from "react-router-dom";

export default class Register extends Component {

  state = {
    username: "",
    email: "",
    password: "",
    successful: false,
    message: "",
    redirect: null
  }
  componentDidMount() {
    const currentUser = LStorageUser.getUser();

    if (!currentUser) {
      this.setState({ redirect: "/login" });
      return;
    }
  }

  handleInputChange = (event) => {
    const {name, value} = event.target;

    this.setState({
      [name]: value
    });
  }

  handleRegister = (event) => {
    event.preventDefault();
    if (isEmpty(this.state.username) || isEmpty(this.state.password) || isEmpty(this.state.email)) {
      const errorMsg = "Заполните поля";

      this.setState({
        message: errorMsg,
        successful: false
      });
      return;
    }

    //TODO: Проверить дqанные на ошибки

    AuthService.register(this.state.username, this.state.email, this.state.password).then(
        (response) => {
          this.setState({
            username: "",
            email: "",
            password: "",
            message: response.data.message,
            successful: true
          });
        }).catch((error)=> {
          let errorMsg =  error.response?.data?.message || error.message;

          this.setState({
            successful: false,
            message: errorMsg
          });
    });
  }


  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }
    return (
      <div className="col-md-12">
        <div className="card card-container">
          <h3 className="text-muted">Auth</h3>
          <form>
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <input className="form-control mb-2"
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
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input className="form-control"
                     required
                     name="email"
                     type="email"
                     onChange={this.handleInputChange}
                     value={this.state.email}
                     placeholder="Email"
              />
            </div>
            <br/>
            <div className="form-group d-flex justify-content-between">
              <button className="btn btn-primary btn-block"
                      onClick={this.handleRegister}>
                <span>Register</span>
              </button>
            </div>
            {this.state.message && (
                <div className="form-group">
                  <p style={{color: 'red'}}>{this.state.message}</p>
                </div>
            )
            }
          </form>
        </div>
      </div>
    );
  }
}
