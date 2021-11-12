import React from "react";
import News from "./News";
import "./News.css";

const NewsRow = (props) => {
    console.log(props.news);
    return(
        <div>
            <div className="row-news d-flex justify-content-between">
            {
                props.news.map(param => {
                    return <News content={param}/>
                })
            }
            </div>
        </div>
    );
}

export default NewsRow;
