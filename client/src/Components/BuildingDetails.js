import React from 'react'
import Button from "react-bootstrap/Button";

export default function BuildingDetails({building, sellBuilding}) {

    function handleSellBuilding() {
        sellBuilding(building.positionId);
    }

    return (
        <div className="building-details">
            <p>NAME: {building.buildingType.name}</p>
            <p>MAINTENANCE: £{building.buildingType.cost}</p>
            <p>REPUTATION INCREASE: {building.buildingType.reputationMultiplier}</p>
            <Button variant="dark-jurassik" onClick={handleSellBuilding}>SELL</Button>
        </div>
    )
}