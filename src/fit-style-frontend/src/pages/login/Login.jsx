import React from 'react';

import "./Login.css";
import {LoginBackGround} from "./LoginBackGround";
import {Logo} from "./Logo";

const Login = ({handleLogin, setModalActive, emailState , passwordState}) => {
    const handleRecoverPassword = (event) => {event.preventDefault(); setModalActive(true)}
    return (
      <div>
         <Logo/>
          <div className="col-md-12">
              <div className="card card-container">
                  <LoginBackGround/>
                  <form className="form-div">
                      <div className="form-group">
                          <label htmlFor="username"/>
                          <input className="form-control form-control-auth" {...emailState}/>
                      </div>
                      <div className="form-group">
                          <label htmlFor="password"/>
                          <input className="form-control form-control-auth" {...passwordState} autoComplete="on"/>
                      </div>
                      <div className="d-flex justify-content-between mt-3">
                          <button className="btn btn-secondary btn-entry"
                                  onClick={handleLogin}>
                              Войти
                          </button>
                          <button className="btn text-white"
                                  onClick={handleRecoverPassword}>
                              Забыли пароль?
                          </button>
                      </div>
                  </form>
              </div>
          </div>
      </div>
    );
}

export default Login;