import React from 'react';
import "./Modal.css"

const Modal = ({active, setActive, children}) => {
    return (
        <div className={active ? "modal_ active" : "modal_"} onClick={() => setActive(false)}>
            <div className={active ? "modal-content_ active" : "modal-content_"} onClick={event => event.stopPropagation()}>
                {children}
            </div>
        </div>
    );
};

export default Modal;