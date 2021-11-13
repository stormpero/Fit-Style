import React, { Component } from "react";
import "./Login.css";
import AuthService from "../../services/AuthService";
import isEmpty from "validator/es/lib/isEmpty";
import {Redirect} from "react-router-dom";
import LStorageUser from "../../services/LStorageUser";

export default class Login extends Component {

  state = {
    email: "",
    password: "",
    isLoading: false,
    errorMsg: "",
    redirect: null
  }
  componentDidMount() {
    const currentUser = LStorageUser.getUser();
    if (currentUser) this.setState({ redirect: "/user" });
  }

  handleInputChange = (event) => {
    const {name, value} = event.target;

    this.setState({
      [name]: value
    });
  }

  handleLogin = (event) => {
    event.preventDefault();
    if (isEmpty(this.state.email) || isEmpty(this.state.password)) {
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

    AuthService.login(this.state.email, this.state.password).then(
      () => {
        this.props.history.push("/user");
        window.location.reload();
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
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }
    return (
      <div className="col-md-12">
        <div className="card card-container">
          <ul className="list-inline">
            <li className="list-inline-item head1">Go</li>
            <li className="list-inline-item head2">Beyond</li>
            <li className="list-inline-item head3">Limits</li>
          </ul>
          <ul className="list-inline">
            <li className="list-inline-item head2">Fit</li>
            <li className="list-inline-item head3">Style</li>
          </ul>
          <div className="bg"/>
          <div className="bg bg2"/>
          <div className="bg bg3"/>
          <form className="form-div">
            <div className="form-group">
              <label htmlFor="username"/>
              <input className="form-control form-control-auth"
                  required
                  name="email"
                  type="text"
                  onChange={this.handleInputChange}
                  value={this.state.usernameEmail}
                  placeholder="Email"
              />
            </div>
            <div className="form-group">
              <label htmlFor="password"/>
              <input className="form-control form-control-auth"
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
              <button className="btn btn-secondary"
                onClick={this.handleLogin}>
                Войти
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