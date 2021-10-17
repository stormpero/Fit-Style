import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/authService";

    import { Routes } from "./pages/routes/routes"

// import AuthVerify from "./common/auth-verify";
import EventBus from "./services/EventBus";

class App extends Component {
  state = {
    isAdmin: false,
    currentUser: undefined,
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        isAdmin: user.roles.includes("ROLE_MODERATOR"),
      });
    }
    
    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      isAdmin: false,
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser, isAdmin } = this.state;
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark ">
          <Link to={"/"} className="navbar-brand logo">
            Fit-Style
          </Link>
          <div className="navbar-nav mr-auto">
            { currentUser && (
                <div className="navbar-nav ml-auto">
                  <li className="nav-item">
                    <Link to={"/profile"} className="nav-link">
                      Профиль
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link to={{
                      pathname: "/user",
                      state: {
                        username: currentUser.username
                      }
                    }} className="nav-link">
                      Контент
                    </Link>
                  </li>
                </div>
            )}
            { isAdmin && (
                <li className="nav-item">
                  <Link to={"/register"} className="nav-link">
                    Регистрация клиента
                  </Link>
                </li>
            )}
            { currentUser && (
                <div className="navbar-nav ml-auto">
                  <li className="nav-item">
                    <a href="/login" className="nav-link" onClick={this.logOut}>
                      Выйти
                    </a>
                  </li>
                </div>
            )}
          </div>
        </nav>

        <Routes/>

        { /*<AuthVerify logOut={this.logOut}/> */ }
      </div>
    );
  }
}

export default App;
