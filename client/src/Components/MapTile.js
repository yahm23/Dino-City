import React from 'react';
import Col from 'react-bootstrap/Col';
import Image from "react-bootstrap/Image";

export default function MapTile({img, children}) {

// const mapImg = (selectedImg) => {
//     return `/island/${selectedImg}.png`
// };

    return(
        <Col>
        <div className="map-tile-wrapper">
            <Image className="map-tile" src={img} fluid/>
            {children}
        </div>
        </Col>
    )

}