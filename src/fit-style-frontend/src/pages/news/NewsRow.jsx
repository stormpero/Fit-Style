import React from "react";
import News from "./News";
import "./News.css";

const NewsRow = (props) => {
    return(
        <div>
            <div className="row-news d-flex justify-content-start">
                {props.news.map((param, index) => <News key={index} content={param} delete={props.delete} deleteMode={props.deleteMode}/>)}
            </div>
        </div>
    );
}

export default NewsRow;
