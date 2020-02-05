import React, {useState, useEffect} from 'react';
import MapBox from '../Components/MapBox';
import MapTileRow from '../Components/MapTileRow';
import MapTile from '../Components/MapTile';
import BuildEnclosure from '../Components/BuildEnclosure';
import BuildBuilding from '../Components/BuildBuilding';
import BuildingDetails from '../Components/BuildingDetails';
import DinoPopup from "../Components/DinoPopup";
import {Redirect} from 'react-router-dom';
import GameStats from '../Components/GameStats';
import EnclosureTile from '../Components/EnclosureTile';
import EnclosureDetail from '../Components/EnclosureDetail';
import EmptyTileForEnclosure from "../Components/EmptyTileForEnclosure";
import EmptyTileForBuilding from "../Components/EmptyTileForBuilding"
import GameHeader from "../Components/GameHeader";
import GameTitle from "../Components/GameTitle";
import RandomEvent from '../Components/RandomEvent';
import BuildingTile from '../Components/BuildingTile';

function GamePage({parkName}) {
    const [showPopup, setShowPopup] = useState(false);
    const [showEnclosure, setShowEnclosure] = useState(false);
    const [showBuyBuildingPopup, setShowBuyBuildingPopup] = useState(false);
    const [showBuildingDetailsPopup, setShowBuildingDetailsPopup] = useState(false);
    const [park, setPark] = useState({money: 12000, enclosures:[], buildings: []});
    const [stats, setStats] = useState({money: 0, income:0, population: 0 });
    const [position, setPosition] = useState(null);
    const [enclosures, setEnclosures] = useState({});
    const [buildings, setBuildings] = useState({})
    const [dinosaurs, setDinosaurs] = useState([]);
    const [eventMessage, setEventMessage] = useState("")


    useEffect(() => {
       fetchStats()
        setInterval(() => {
            fetchStats()
        }, 5000)
        setInterval(() => {
            fetchEvent()
        },60000)
        fetchPark();
        fetchEnclosures();
        fetchDinosaurs();
        fetchBuildings();
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

    function fetchBuildings() {
        if(parkName) {
            fetch(`http://localhost:8080/buildings/types`)
            .then(res => res.json())
            .then(data => setBuildings(data.types))
        }
    }

    function fetchDinosaurs() {
        if(parkName) {
            fetch(`http://localhost:8080/dinosaur/species`)
                .then(res => res.json())
                .then(data => setDinosaurs(data.species))
        }
    }

    function fetchEvent() {
        if(parkName) {
            fetch(`http://localhost:8080/park/name/${parkName}/event`)
                .then(res => res.json())
                .then(data => setEventMessage(data.message)) 
        }
    }

    const handleOnOpenPopup = (position) => {
        setShowPopup(true);
        setPosition(position);
    };

    const handleOnClosePopup = () => {
        setShowPopup(false);
        setShowEnclosure(false);
        setShowBuyBuildingPopup(false);
        setShowBuildingDetailsPopup(false);
    };

    function handleOnOpenPopEnclosure(position) {
        setShowEnclosure(true);
        setPosition(position);
    }


    function handleOnOpenPopBuildBuilding(position) {
        setShowBuyBuildingPopup(true);
        setPosition(position);
    }

    function handleOnOpenPopBuildingDetails(position) {
        setShowBuildingDetailsPopup(true);
        setPosition(position);
    }



    const buyEnclosure = (size, security) => {    
      setShowPopup(false);
      fetch(`http://localhost:8080/park/enclosure/${parkName}/${size.size}/${security.security}/${position}`)
          .then(() => fetchPark())
          .then(() => fetchStats())
    };

    const buyDinosaur = (dinosaur) => {
        // setShowEnclosure(false);
        fetch(`http://localhost:8080/park/name/${parkName}/enclosure/${position}/dinosaur/${dinosaur.toUpperCase()}`)
            .then(() => fetchPark())
            .then(() => fetchStats())
    };

    const buyBuilding = (buildingType) => {
        setShowBuyBuildingPopup(false);
        console.log(buildingType)
        fetch(`http://localhost:8080/park/name/${parkName}/building/${buildingType}/${position}`, {method: "POST"})
            .then(() => fetchPark())
            .then(() => fetchStats())
    }

    const sellDinosaur = (id) => {
        //finish function once we have endpoint
    }
    
    const sellBuilding = (position) => {
        setShowBuyBuildingPopup(false);
        fetch(`http://localhost:8080/park/name/${parkName}/building/${buildingType}/${position}`, {method: "DELETE"})
        .then(() => fetchPark())
        .then(() => fetchStats())
    }

    function renderRedirect() {
        return <Redirect to="/" />
    }

    function initializeEndGame() {
        return <Redirect to="/game-over"/>
    }

    function getEnclosure() {
        return park.enclosures.find(enclosure => enclosure.positionId === position);
    }

    function getBuilding() {
        return  park.buildings.find(building => building.positionId === position);
    }
 

  return (
    <>
        {!parkName && renderRedirect()}
        {park.money<= 0 && initializeEndGame()}

        <GameHeader>
            <GameTitle parkName={parkName}/>
            <GameStats stats={stats}/>
        </GameHeader>

        <DinoPopup show={showPopup} handleClose={handleOnClosePopup}>
            <BuildEnclosure money={park.money} buyEnclosure={buyEnclosure} enclosures={enclosures}/>
        </DinoPopup>
        <DinoPopup show={showEnclosure} handleClose={handleOnClosePopup}>
            <EnclosureDetail
                money={park.money}
                dinosaurs={dinosaurs}
                enclosure={getEnclosure()}
                buyDinosaur={buyDinosaur}
                sellDinosaur={sellDinosaur}/>
        </DinoPopup>
        <DinoPopup show={eventMessage != ""} handleClose={handleOnClosePopup}>
            <RandomEvent eventMessage={eventMessage}/>
        </DinoPopup>
        <DinoPopup show={showBuyBuildingPopup} handleClose={handleOnClosePopup}>
            <BuildBuilding money={park.money} buildings={buildings} buyBuilding={buyBuilding} />
        </DinoPopup>
        <DinoPopup show={showBuildingDetailsPopup} handleClose={handleOnClosePopup}>
            <BuildingDetails building={getBuilding()} sellBuilding={sellBuilding}/>
        </DinoPopup>

      <MapBox>
        <MapTileRow>
            <MapTile img={"grass_01"}></MapTile>
            <MapTile img={"grass_02"}>
                <PrepareEnclosureTile 
                    park={park} 
                    position={1} 
                    onEmptyEnclosureClick={handleOnOpenPopup}
                    onEnclosureClick={handleOnOpenPopEnclosure}
                    />
                </MapTile>
            <MapTile img={"grass_03"}></MapTile>
        </MapTileRow>
        <MapTileRow>
            <MapTile img={"grass_04"}></MapTile>
            <MapTile img={"grass_05"}>
                <PrepareEnclosureTile 
                    park={park} 
                    position={2} 
                    onEmptyEnclosureClick={handleOnOpenPopup}
                    onEnclosureClick={handleOnOpenPopEnclosure}
                    />
            </MapTile>
            <MapTile img={"grass_06"}>
                <PrepareBuildingTile 
                    park={park} 
                    position={3} 
                    onEmptyBuildingClick={handleOnOpenPopBuildBuilding}
                    onBuildingClick={handleOnOpenPopBuildingDetails}
                    />
            </MapTile>
        </MapTileRow>
      </MapBox>
    </>
  );
}

const PrepareEnclosureTile = ({park, position, onEmptyEnclosureClick, onEnclosureClick}) => {
    const foundEnclosure = park.enclosures.find(enclosure => enclosure.positionId === position)
    if (foundEnclosure){
        return <EnclosureTile enclosure={foundEnclosure} onClick={onEnclosureClick} position={position}/>
    }
    return <EmptyTileForEnclosure onClick={onEmptyEnclosureClick} position={position}/>
};

const PrepareBuildingTile = ({park, position, onEmptyBuildingClick,  onBuildingClick}) => {
    const foundBuilding = park.buildings.find(building => building.positionId === position)
    if(foundBuilding) {
        return <BuildingTile building={foundBuilding} onClick={onBuildingClick}  position={position}  />
    }
    return <EmptyTileForBuilding onClick={onEmptyBuildingClick} position={position} />
}

export default GamePage