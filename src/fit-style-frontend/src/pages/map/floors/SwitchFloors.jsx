import React from 'react';

export const SwitchFloors = ({setIsFirstFloor}) => {
    return (
        <center>
            <div className="switch-button">
                <input className="switch-button-checkbox" type="checkbox" onClick={() => setIsFirstFloor(prev => !prev)}/>
                <label className="switch-button-label" htmlFor="">
                    <span className="switch-button-label-span">1 Этаж</span>
                </label>
            </div>
        </center>
    );
};