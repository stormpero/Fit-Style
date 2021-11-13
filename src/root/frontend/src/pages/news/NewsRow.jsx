import React from "react";
import News from "./News";
import "./News.css";

const NewsRow = (props) => {
    return(
        <div>
            <div className="row-news d-flex justify-content-between">
            {
                props.news.map((param, index) => <News key={index} content={param}/>)
            }
            </div>
        </div>
    );
}

export default NewsRow;
