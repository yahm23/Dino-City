import React from 'react';
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";

export default function BuildingItem({money, building, buyBuilding}) {

    const handleOnClick = () => {
        buyBuilding(building.name); 
    };

    const isBuildingAvailable = () => {
        return building.price <= money;
    };

    return(
        <>
            <Image src={`./buildings/${building.name}-building.png`.toLowerCase()} fluid/>
            <p><b>{building.name}</b></p>
            <p>{building.price}</p>
            <p>{building.reputationMultiplier}</p>
            <p>{building.cost}</p>
            <Button
                    onClick={handleOnClick}
                    disabled={!isBuildingAvailable()}>
                        Buy
            </Button> <br/>
        </>
    )
} 