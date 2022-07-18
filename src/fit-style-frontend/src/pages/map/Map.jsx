import React, {useState} from 'react';
import "./Map.css"
import {FirstFloor} from "./floors/FirstFloor";
import {SecondFloor} from "./floors/SecondFloor";
import {SwitchFloors} from "./floors/SwitchFloors";

export const Map = () => {
    const [isFirstFloor, setIsFirstFloor] = useState(true);
    return (
        <div className="map-par">
            <h1 className="map-title">Карта зала</h1>
            <SwitchFloors setIsFirstFloor = {setIsFirstFloor}/>
            {isFirstFloor ? <FirstFloor/> : <SecondFloor/>}
        </div>
    );
};

