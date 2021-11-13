import React from 'react';

import "./Login.css";

const Login = (props) => {
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
                             onChange={props.handleFunc.input}
                             value={props.value.email}
                             placeholder="Email"
                      />
                  </div>
                  <div className="form-group">
                      <label htmlFor="password"/>
                      <input className="form-control form-control-auth"
                             required
                             name="password"
                             type="password"
                             onChange={props.handleFunc.input}
                             value={props.value.password}
                             placeholder="Password"
                      />
                  </div>
                  <br/>
                  <div className="form-group d-flex justify-content-between">
                      <button className="btn btn-secondary"
                              onClick={props.handleFunc.login}>
                          Войти
                      </button>
                  </div>
                  {props.error && (
                      <div className="form-group">
                          <p style={{color: 'red'}}>{props.error}</p>
                      </div>
                  )
                  }
              </form>
          </div>
      </div>
  );
}

export default Login;