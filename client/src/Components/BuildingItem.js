import React from 'react';
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export default function BuildingItem({money, building, buyBuilding}) {

    const handleOnClick = () => {
        buyBuilding(building.name); 
    };

    const isBuildingAvailable = () => {
        return building.price <= money;
    };

    return(
        <Col>
            <div class="vl">
                <Image src={`./buildings/${building.name}-building.png`.toLowerCase()} fluid/>
                <p><b>{building.name}</b></p>
                <p>£{building.price}</p>
                <p>Reputation Increase: {building.reputationMultiplier}</p>
                <p>Daily Expense: £{building.cost}</p>
                <Button
                        variant={"dark-jurassik"}
                        onClick={handleOnClick}
                        disabled={!isBuildingAvailable()}>
                            Buy
                </Button> 
                
            </div>
        </Col>
    )
} 