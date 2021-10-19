import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import logo from "./assets/logo.png"
import AuthService from "./services/authService";
import { Routes } from "./pages/routes/routes";
import Navbar from "./components/navbar/Navbar";

// import AuthVerify from "./common/auth-verify";
import EventBus from "./services/EventBus";

class App extends Component {
  state = {
    currentUser: undefined,
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user
      });
    }
  }

  render() {
    const currentUser  = this.state.currentUser;
    return (
      <div>
          { !currentUser &&
            <div className="d-flex justify-content-center">
              <Link to={"/"} className="navbar-brand ">
                <img className="logo" src={logo}/>
              </Link>
            </div>
          }
          <div className="navbar-nav mr-auto">
            { currentUser && (
                <Navbar/>
            )}
          </div>


        <Routes/>

        { /*<AuthVerify logOut={this.logOut}/> */ }
      </div>
    );
  }
}

export default App;
