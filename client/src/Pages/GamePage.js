import React, {useState, useEffect} from 'react';
import MapBox from '../Components/MapBox';
import MapTileRow from '../Components/MapTileRow';
import MapTile from '../Components/MapTile';
import BuildEnclosure from '../Components/BuildEnclosure';
import DinoPopup from "../Components/DinoPopup";
import {Redirect} from 'react-router-dom';
import GameStats from '../Components/GameStats';
import BuildingTile from '../Components/BuildingTile';
import EnclosureDetail from '../Components/EnclosureDetail';
import EmptyTile from "../Components/EmptyTile";
import GameHeader from "../Components/GameHeader";
import GameTitle from "../Components/GameTitle";

function GamePage({parkName}) {
    const [showPopup, setShowPopup] = useState(false);
    const [showEnclosure, setShowEnclosure] = useState(false);
    const [park, setPark] = useState({money: 12000, enclosures:[]});
    const [stats, setStats] = useState({money: 0, income:0, population: 0 });
    const [position, setPosition] = useState(null);
    const [enclosures, setEnclosures] = useState({});
    const [dinosaurs, setDinosaurs] = useState([]);


    useEffect(() => {
       fetchStats()
        setInterval(() => {
            fetchStats()
        }, 5000)
        fetchPark();
        fetchEnclosures();
        fetchDinosaurs();
    },[]);

    const fetchPark = () => {
        if(parkName){
            fetch(`http://localhost:8080/park/name/${parkName}`)
            .then(res => res.json())
            .then(park => setPark(park))
        }
    }

    function fetchStats() {
        if(parkName){
            fetch(`http://localhost:8080/park/stats/name/${parkName}`)
            .then(res => res.json())
            .then(data => setStats(data))
        }
    }

    function fetchEnclosures() {
        if(parkName) {
            fetch(`http://localhost:8080/enclosures/types`)
                .then(res => res.json())
                .then(data => setEnclosures(data))
        }
    }
    function fetchDinosaurs() {
        if(parkName) {
            fetch(`http://localhost:8080/dinosaur/species`)
                .then(res => res.json())
                .then(data => setDinosaurs(data.species))
        }
    }

    const handleOnOpenPopup = (positionN) => {
        setShowPopup(true);
        setPosition(positionN);
    };

    const handleOnClosePopup = () => {
        setShowPopup(false);
        setShowEnclosure(false);
        setPosition(null);
    };

    function handleOnOpenPopEnclosure() {
        setShowEnclosure(true);
    }

    const buyEnclosure = (size, security) => {
      console.log(size);
      console.log(security);     
      setShowPopup(false);
      fetch(`http://localhost:8080/park/enclosure/${parkName}/${size.size}/${security.security}/${position}`)
          .then(() => fetchPark())
          .then(() => fetchStats())
    };

    function renderRedirect() {
        return <Redirect to="/" />
    }

    function getEnclosure(position, enclosures) {
        return 
    }
 

  return (
    <>
        {!parkName && renderRedirect()}
        <GameHeader>
            <GameTitle parkName={parkName}/>
            <GameStats stats={stats}/>
        </GameHeader>
        <DinoPopup show={showPopup} handleClose={handleOnClosePopup}>
            <BuildEnclosure money={park.money} buyEnclosure={buyEnclosure} enclosures={enclosures}/>
        </DinoPopup>
        <DinoPopup show={showEnclosure} handleClose={handleOnClosePopup}>
            <EnclosureDetail money={park.money} dinosaurs={dinosaurs} position={position}/>
        </DinoPopup>
      <MapBox>
        <MapTileRow>
            <MapTile img={"grass_01"}></MapTile>
            <MapTile img={"grass_02"}>
                <PrepareBuildingTile 
                    park={park} 
                    position={1} 
                    onEmptyBuildingClick={handleOnOpenPopup}
                    onEnclosureClick={handleOnOpenPopEnclosure}
                    />
                </MapTile>
            <MapTile img={"grass_03"}></MapTile>
        </MapTileRow>
        <MapTileRow>
            <MapTile img={"grass_04"}></MapTile>
            <MapTile img={"grass_05"}>
                <PrepareBuildingTile 
                        park={park} 
                        position={2} 
                        onEmptyBuildingClick={handleOnOpenPopup}
                        onEnclosureClick={handleOnOpenPopEnclosure}
                        />
            </MapTile>
            <MapTile img={"grass_06"}></MapTile>
        </MapTileRow>
      </MapBox>
    </>
  );
}

const PrepareBuildingTile = ({park, position, onEmptyBuildingClick, onEnclosureClick}) => {
    const foundEnclosure = park.enclosures.find(enclosure => enclosure.positionId === position)
    if (foundEnclosure){
        return <BuildingTile enclosure={foundEnclosure} onClick={onEnclosureClick} position={position}/>
    }
    return <EmptyTile onClick={onEmptyBuildingClick} position={position}/>
};

export default GamePage;
