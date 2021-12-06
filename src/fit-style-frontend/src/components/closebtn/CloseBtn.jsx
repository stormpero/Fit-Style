import React from 'react';
import "./CloseBtn.css"

export const CloseBtn = ({onClickEvent, classname = ""}) => {
    return (
        <div className={classname}>
            <button
                type="button"
                className="btn-close-modal"
                data-dismiss="modal"
                aria-label="Close"
                onClick={onClickEvent}
            >
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    );
};

