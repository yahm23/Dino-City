import React from 'react';
import Col from 'react-bootstrap/Col';

function MapTile({img, children}) {

const mapImg = (selectedImg) => {
    return `/tiles/${selectedImg}.jpg`
}

    return(
        <Col>
        <div className="map-tile-wrapper">
            <img className="map-tile" src={mapImg(img)}></img>
            {children}
        </div>
        </Col>
    )

}
export default MapTile;