import React from 'react';
import Button from 'react-bootstrap/Button';
import Image from "react-bootstrap/Image";

export default function BuildingTile({onClick, position, building}) {


    const buildingImg = () => {
        if(building) {
            return `./buildings/${building.buildingType.name}-building.png`.toLowerCase()
        }
    }

    const handleOnClick = () => {
        onClick(position);
    }

    return (
        <Button onClick={handleOnClick} bsPrefix="building-btn">
            <Image src={buildingImg()} fluid/>
        </Button>
    )
}