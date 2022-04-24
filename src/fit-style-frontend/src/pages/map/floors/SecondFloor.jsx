import React from 'react';
import second from "../../../assets/second-floor.png";

export const SecondFloor = () => {
    return(
        <div className={"map second-floor"}>
            <svg className="svg-map" viewBox="0 0 1038 584">
                <g>
                    <path className="part" d="m 562.57783,426.08969 -24.93791,69.18259 160.0853,39.41799 18.50232,-71.05964 z"/>
                    <text x="708" y="330" className="text-coaching"> Тренерская </text>
                </g>
                <g>
                    <path className="part" d="m 52.289169,371.11903 160.889751,-217.46932 374.06867,78.56783 49.60767,-127.1029 159.01271,30.56905 -32.71425,138.90148 203.52553,42.63579 -1.87704,38.88169 -46.65803,-10.99413 1.87705,-11.79859 -218.27377,-47.73062 2.41335,-23.59717 -87.95306,-18.23417 -4.55855,10.72599 0.5363,13.94377 -81.78562,224.17306 z"/>
                    <text x="300" y="230" className="text-training"> Тренажерный зал </text>
                </g>
                <g>
                    <path className="part" d="M 295.50084,42.903934 628.00633,104.57834 584.56609,218.81006 221.22341,142.92373 Z"/>
                    <text x="340" y="40" className="text-aerobics"> Зал аэробики </text>
                </g>
                <g>
                    <path className="part" d="M 719.17719,451.29575 753.76848,310.51722 614.3307,282.09336 567.13637,414.55926 Z"/>
                    <text x="-297" y="750" className="text-cloakroom-male-second male"> М </text>
                    <text x="-362" y="790" className="text-cloakroom-male-second"> раздевалка </text>
                </g>
                <g>
                    <path className="part" d="m 835.28596,478.64701 -110.47763,-25.74236 34.59129,-142.92373 153.11342,33.78685 -12.06673,100.55609 -60.33366,-12.06673 -4.02224,12.87118 4.55854,0.80445 z"/>
                    <text x="-345" y="875" className="text-cloakroom-female-second male"> W </text>
                    <text x="-410" y="910" className="text-cloakroom-female-second"> раздевалка </text>
                </g>
            </svg>
            <img className="map-img second" src={second} alt="Fit-Style"/>
        </div>
    )
}
