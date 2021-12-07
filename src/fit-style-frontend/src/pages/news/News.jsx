import React from "react";
import "./News.css";

import Gym from "../../assets/gym.jpg";
import {CloseBtn} from "../../components/closebtn/CloseBtn";

const News = ({deleteMode, content, deleteNews}) => {
    let image, color;
    if (content.img) {
         image = content.img;
         color = 0.6;
    } else {
         image = Gym;
         color = 1;
    }

    const style = {
        background: `linear-gradient(rgba(0, 0, 0, ${color}), rgba(0, 0, 0, 0.4)), center / cover no-repeat url("${image}")`,
    }

    return(
        <div className={"container-fluid block-news" + (deleteMode ? "" : " none-delete-mode")} style={style}>
            {deleteMode && <CloseBtn onClickEvent={() => deleteNews(content.id)}/> }
            <h2 className="title">{content.header}</h2>
            <div className="dateTime">
                <p>{content.dateTime}</p>
            </div>
            <div className="content-news d-flex flex-column">
                <h2 className="title-news">{content.header}</h2>
                <p className="content-news-text fw-light">{content.content}</p>
                <div>
                    <p>{content.dateTime}</p>
                </div>
            </div>
        </div>

    );
}

export default News;