import React, {useState} from 'react';

import "./Map.css"
import first from "../../assets/first-floor.png"
import second from "../../assets/second-floor.png"

export const Map = () => {
    const [MapActive, setMapActive] = useState(true);
    return (
        <div className="map-par">
            <h1 className="map-title">Карта зала</h1>
            <center>
                <div className="switch-button">
                    <input className="switch-button-checkbox" type="checkbox" onClick={() => setMapActive((prev) => !prev)}/>
                    <label className="switch-button-label" htmlFor="">
                        <span className="switch-button-label-span">1 Этаж</span>
                    </label>
                </div>
            </center>
            <div className={MapActive ? "map first-floor" : "map-none"}>
                <svg className="svg-map" viewBox="0 0 1038 546">
                    <g>
                        <path className="part st0" d="M 399.71901,77.635713 263.33195,265.43021 615.84005,373.49073 707.11447,150.02577 400.76814,74.750602 Z"/>
                        <text x="475" y="100" className="text-pool"> Бассейн </text>
                    </g>
                    <g>
                        <path className="part" d="m 752.97554,461.42935 49.70381,-160.98098 186.2038,50.44565 -13.35326,83.82881 -38.57609,-10.38587 -11.86956,42.28532 -48.96196,-6.67663 -8.90217,35.6087 z" />
                        <text x="400" y="720" className="text-reception"> Рецепция </text>
                    </g>
                    <g>
                        <path className="part" d="M 666.9212,432.49728 640.21467,508.16576 428.04555,439.586 464.50286,371.39247 Z" />
                        <text x="675" y="255" className="text-hamam"> Хамам </text>
                    </g>
                    <g>
                        <path className="part" d="M 291.65849,318.67362 454.27383,367.98279 419.12794,436.4386 248.90639,381.88378 Z" />
                        <text x="465" y="260" className="text-massage"> Массаж </text>
                    </g>
                    <g>
                        <path className="part" d="m 140.32131,345.95103 96.78235,31.99851 44.06352,-64.78386 -88.91388,-33.30992 z" />
                        <text x="330" y="260" className="text-massage"> Сауна </text>
                    </g>
                    <g>
                        <path className="part" d="M 37.475112,313.18487 282.13406,30.515448 364.57931,49.788363 139.19327,345.84175 Z" />
                        <text x="-190" y="280" className="text-rest"> Зона отдыха </text>
                    </g>
                    <g>
                        <path className="part" d="m 853.49592,302.30299 26.70653,-105.34239 -137.61278,-34.86685 -82.71603,216.99049 65.65353,18.17527 -13.35326,38.94701 42.28533,12.24049 47.84918,-158.38451 z" />
                        <text x="-240" y="830" className="text-cloakroom-male"> W раздевалка </text>
                    </g>
                    <g>
                        <path className="part" d="M 992.59239,338.65353 1025.9755,114.24457 883.16984,78.264946 852.01223,187.6875 l 32.59494,8.48488 -27.86036,106.63313 z" />
                        <text x="-175" y="975" className="text-cloakroom-female"> M раздевалка </text>
                    </g>
                </svg>
                <img className="map-img first" src={first} alt="Fit-Style"/>
            </div>
            <div className={!MapActive ? "map second-floor" : "map-none"}>
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
        </div>
    );
};

