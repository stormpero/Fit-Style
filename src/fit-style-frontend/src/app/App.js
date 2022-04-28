import React from "react";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AppRouter from "../components/approuter/AppRouter";
import {AuthProvider} from "../packages/auth/AuthProvider";

const App = () => {
    return (
      <AuthProvider>
        <AppRouter/>
      </AuthProvider>
    )
}

export default App;