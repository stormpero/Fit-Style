import React, { Component } from "react";
import "./Profile.css";
import { Redirect } from "react-router-dom";
import LStorageUser from "../../services/LStorageUser";
import ProfilePicture from "../../assets/default-profile-picture.jpg";

export default class Profile extends Component {
  state = {
    userReady: false,
    currentUser: { username: "" }
  }
  
  componentDidMount() {
    const currentUser = LStorageUser.getUser();
    this.setState({ currentUser: currentUser, userReady: true })
  }

  render() {
    const { currentUser } = this.state;

    return (

      <div className="container profile-info">
        {
          (this.state.userReady) ?
          <div className="d-flex justify-content-between">
            <div className="left-image">
              <img className="picture-profile" src={ProfilePicture} alt="Fit-Style"/>
            </div>
            <div className="right-info d-flex justify-content-between">
              <div className="first-column">
              <p>
                <label> ID </label>
                <strong>{currentUser.id}</strong>
              </p>
              <p>
                <label> ФИО </label>
                <strong>{currentUser.name} </strong>
                <strong>{currentUser.surname} </strong>
                <strong>{currentUser.patronymic} </strong>
              </p>
              <p>
                <label> Возраст </label>
                <strong>{currentUser.age}</strong>
              </p>
              <p>
                <label> Пол </label>
                <strong>{currentUser.gender}</strong>
              </p>
              <p>
                <label> Дата рождения </label>
                <strong>{currentUser.birthdate}</strong>
              </p>
              </div>
              <div className="second-column">
              <p>
                <label> Телефон </label>
                <strong>{currentUser.telephone}</strong>
              </p>
              <p>
                <label> Паспорт </label>
                <strong>{currentUser.passport}</strong>
              </p>
              <p>
                <label> Адрес </label>
                <strong>{currentUser.address}</strong>
              </p>
              <p>
                <label> Вид абонемента </label>
                <strong>{currentUser.subscription}</strong>
              </p>
              <p>
                <label> Дата окончания </label>
                <strong>{currentUser.endDate}</strong>
              </p>
              </div>
            </div>
          </div>: null
        }
      </div>
    );
  }
}
