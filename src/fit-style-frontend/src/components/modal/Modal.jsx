import React from 'react';
import "./Modal.css"
import {CloseBtn} from "../closebtn/CloseBtn";

const Modal = ({active, setActive, options, children}) => {
    return (
        <div className={active ? "modal_ active" : "modal_"} onClick={options?.closeBackground ? () => setActive(false) : null}>
            <div className={active ? "modal-content_ active" : "modal-content_"} onClick={event => event.stopPropagation()}>
                <CloseBtn onClickEvent={() => setActive(false)}/>
                {children}
            </div>
        </div>
    );
};

export default Modal;