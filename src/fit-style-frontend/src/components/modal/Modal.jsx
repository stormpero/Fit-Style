import React from 'react';
import "./Modal.css"

const Modal = ({active, setActive, options, children}) => {
    return (
        <div className={active ? "modal_ active" : "modal_"} onClick={options?.closeBackground ? () => setActive(false) : null}>
            <div className={active ? "modal-content_ active" : "modal-content_"} onClick={event => event.stopPropagation()}>
                <button
                    type="button"
                    className="btn-close-modal"
                    data-dismiss="modal"
                    aria-label="Close"
                    onClick={() => setActive(false)}
                >
                    <span aria-hidden="true">&times;</span>
                </button>
                {children}
            </div>
        </div>
    );
};

export default Modal;