import React from 'react';
import Button from 'react-bootstrap/Button';
import MapBox from './Components/MapBox';
import MapTileRow from './Components/MapTileRow';
import MapTile from './Components/MapTile';
import Enclosure from './Components/Enclosure';

function App() {
  return (
    <>
      <h1>Dino Park</h1>
      <Enclosure />

      <MapBox>
        <MapTileRow>
            <MapTile img={"grass_01"}></MapTile>
            <MapTile img={"grass_02"}><button>IMAGE</button></MapTile>
            <MapTile img={"grass_03"}></MapTile>
        </MapTileRow>
        <MapTileRow>
            <MapTile img={"grass_04"}></MapTile>
            <MapTile img={"grass_05"}><button>IMAGE</button></MapTile>
            <MapTile img={"grass_06"}></MapTile>
        </MapTileRow>
      </MapBox>
    </>
  );
}




export default App;
