import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import MapBox from './Components/MapBox';
import MapTileRow from './Components/MapTileRow';
import MapTile from './Components/MapTile';
import Enclosure from './Components/Enclosure';
import DinoPopup from "./Components/DinoPopup";

function App() {
    const [showPopup, setShowPopup] = useState(false);
    const [park, setPark] = useState({money: 3000});

    const handleOnOpenPopup = () => {
        setShowPopup(true);
    };

    const handleOnClosePopup = () => {
        setShowPopup(false);
    }

  return (
    <>
      <h1>Dino Park</h1>

        <DinoPopup show={showPopup} handleClose={handleOnClosePopup}>
            <Enclosure />
        </DinoPopup>
      <MapBox>
        <MapTileRow>
            <MapTile img={"grass_01"}></MapTile>
            <MapTile img={"grass_02"}><Button onClick={handleOnOpenPopup} bsPrefix="building-btn">IMAGE</Button></MapTile>
            <MapTile img={"grass_03"}></MapTile>
        </MapTileRow>
        <MapTileRow>
            <MapTile img={"grass_04"}></MapTile>
            <MapTile img={"grass_05"}><Button onClick={handleOnOpenPopup} bsPrefix="building-btn">IMAGE</Button></MapTile>
            <MapTile img={"grass_06"}></MapTile>
        </MapTileRow>
      </MapBox>
    </>
  );
}




export default App;
