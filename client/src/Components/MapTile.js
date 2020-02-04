import React from 'react';
import Col from 'react-bootstrap/Col';
import Image from "react-bootstrap/Image";

export default function MapTile({img, children}) {

const mapImg = (selectedImg) => {
    return `/tiles/${selectedImg}.jpg`
};

    return(
        <Col>
        <div className="map-tile-wrapper">
            <Image className="map-tile" src={mapImg(img)} fluid/>
            {children}
        </div>
        </Col>
    )

}