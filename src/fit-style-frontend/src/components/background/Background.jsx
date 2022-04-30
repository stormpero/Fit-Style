import React from 'react';
import "./Background.css";

export const Background = () => {
    return (
        <div>
            <ul className="circles">
                {[...new Array(10)].map((el,i) => <li key={i}/>)}
            </ul>
        </div>
    );
};

