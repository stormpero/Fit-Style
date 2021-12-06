import React from "react";
import "./News.css";

import Gym from "../../assets/gym.jpg";
import {CloseBtn} from "../../components/closebtn/CloseBtn";

const News = (props) => {
    let image, color;
    if (props.content.img) {
         image = props.content.img;
         color = 0.6;
    } else {
         image = Gym;
         color = 1;
    }

    const style = {
        background: `linear-gradient(rgba(0, 0, 0, ${color}), rgba(0, 0, 0, 0.4)), url("${image}"), center`,
        backgroundSize: 'cover'
    }

    return(
        <div className={"container-fluid block-news" + (props.deleteMode ? "" : " none-delete-mode")} style={style}>
            {props.deleteMode && <CloseBtn onClickEvent={() => props.delete(props.content.id)}/> }
            <h2 className="title">{props.content.header}</h2>
            <div className="dateTime">
                <p>{props.content.dateTime}</p>
            </div>
            <div className="content-news d-flex flex-column">
                <h2 className="title-news">{props.content.header}</h2>
                <p className="content-news-text fw-light">{props.content.content}</p>
                <div>
                    <p>{props.content.dateTime}</p>
                </div>
            </div>
        </div>

    );
}

export default News;