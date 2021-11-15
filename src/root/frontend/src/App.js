import React, { Component } from "react";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import { Routes } from "./pages/routes/routes";
import Navbar from "./components/navbar/Navbar";
import LStorageUser from "./services/LStorageUser";


class App extends Component {
  state = {
    currentUser: undefined,
  }

  componentDidMount() {
    if (LStorageUser.isExist()) {
      const user = LStorageUser.getUser();
      this.setState({
        currentUser: user
      });
    }
  }

  render() {
    const currentUser  = this.state.currentUser;
    return (
      <div>
        { currentUser && <Navbar/> }
        <Routes/>
      </div>
    );
  }
}

export default App;