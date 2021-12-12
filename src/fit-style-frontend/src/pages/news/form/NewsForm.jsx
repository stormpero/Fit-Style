import React from 'react';

const NewsForm = (props) => {
    return (
        <div>
            <form>
                <div className="form-group">
                    <label htmlFor="header">Заголовок</label>
                    <input className="form-control mb-2"
                           required
                           name="header"
                           type="text"
                           onChange={props.handleFunc.input}
                           value={props.value.header}
                           placeholder="Заголовок"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="content">Контент</label>
                    <textarea  className="form-control mb-2"
                           required
                           name="content"
                           type="textarea"
                           onChange={props.handleFunc.input}
                           value={props.value.content}
                           placeholder="Контент"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="img">Фотография</label>
                    <input className="form-control mb-2"
                           required
                           name="img"
                           type="file"
                           onChange={props.handleFunc.inputImg}
                    />
                </div>
                <div className="form-group d-flex justify-content-between">
                    <button className="btn btn-primary btn-block"
                            onClick={props.handleFunc.add}>
                        <span>Добавить</span>
                    </button>
                </div>
                {props.message && (
                    <div className="form-group">
                        <p style={{color: 'red'}}>{props.message}</p>
                    </div>
                )}
            </form>
        </div>
    );
};

export default NewsForm;