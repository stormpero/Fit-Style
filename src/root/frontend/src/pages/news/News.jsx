import React, { Component } from "react";
import "./News.css";
import ProfilePicture from "../../assets/default-profile-picture.jpg";
import Gym from "../../assets/gym.jpg";

const News = (props) => {

    return(
        <div className="container-fluid block-news">
            <h2 className="title">{props.content.title}</h2>
            <div className="dateTime title">
                <p>{props.content.dateTime}</p>
            </div>
            <div className="content-news d-flex flex-column">
                <h2 className="title-news">{props.content.title}</h2>
                <p className="content-news-text fw-light">{props.content.content}</p>
                <div>
                    <p>{props.content.dateTime}</p>
                </div>
            </div>
        </div>
    );
}

export default News;