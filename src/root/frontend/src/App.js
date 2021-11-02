import React, { Component } from "react";
import { Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import logo from "./assets/logo.png"
import { Routes } from "./pages/routes/routes";
import Navbar from "./components/navbar/Navbar";
import LStorageUser from "./services/LStorageUser";
// import AuthVerify from "./components/AuthVerify";
// import AuthService from "./services/AuthService";

class App extends Component {
  state = {
    currentUser: undefined,
  }

  componentDidMount() {
    const user = LStorageUser.getUser();
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
                <img className="logo" src={logo} alt="Fit-Style"/>
              </Link>
            </div>
          }
          <div className="navbar-nav mr-auto">
            { currentUser && (
                <Navbar/>
            )}
          </div>

        <Routes/>
      </div>
    );
  }
}

export default App;