import React from "react";
import {BrowserRouter} from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AppRouter from "./components/AppRouter";

const App = () => {
    return (
        <BrowserRouter>
            <AppRouter/>
        </BrowserRouter>
    )
}

export default App;