import React from 'react';

export const Users = ({userList}) => {
    return (
        <div className="container">
            <ul className="list-group">
            {userList.map((value,index) =>
                <li key={index} className="list-group-item">{value.id} {value.email} {value.surname} {value.name} {value.patronymic}</li>)}
            </ul>
        </div>
    );
};