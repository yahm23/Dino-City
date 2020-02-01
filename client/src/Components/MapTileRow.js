import React, {Component} from 'react';
import MapTile from './MapTile';
import Row from 'react-bootstrap/Row';


function MapTileRow ({children}) {
        return(
            <Row>
                {children}
            </Row>
        )
    }

export default MapTileRow;