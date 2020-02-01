import React from 'react';
import MapTileRow from './MapTileRow';
import MapTile from './MapTile';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';



function MapBox({children}) {

        const path = [
            '/tiles/grass_01.jpg',
            '/tiles/grass_02.jpg',
            '/tiles/grass_03.jpg',
            '/tiles/grass_04.jpg',
            '/tiles/grass_05.jpg',
            '/tiles/grass_06.jpg'
        ]

        const enclouser = '/enclousers/enclouser.png';

        

        return(
            <Container>
                {children}
            </Container>
        )
}
export default MapBox;