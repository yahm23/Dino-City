import React from 'react';
import MapTileRow from './MapTileRow';
import MapTile from './MapTile';

function MapBox() {

        const path = [
            '/tiles/grass_01.jpg',
            '/tiles/grass_02.jpg',
            '/tiles/grass_03.jpg',
            '/tiles/grass_04.jpg',
            '/tiles/grass_05.jpg',
            '/tiles/grass_06.jpg'
        ]

        const path2 = [
            '/enclousers/enclouser_01.jpg',
            '/enclousers/enclouser_02.jpg',
            '/enclousers/enclouser_03.jpg',
            '/enclousers/enclouser_04.jpg',
            '/enclousers/enclouser_05.jpg',
            '/enclousers/enclouser_06.jpg'
        ]


        return(
            <>
                <div>
                    <MapTile img={path[0]}/>
                    <MapTile img={path2[1]}/>
                    <MapTile img={path[2]}/>
                </div>
                <div>
                    <MapTile img={path[3]}/>
                    <MapTile img={path[4]}/>
                    <MapTile img={path2[5]}/>
                </div>
            </>
        )
}
export default MapBox;