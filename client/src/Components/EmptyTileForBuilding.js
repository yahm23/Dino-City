import React from 'react';
import Button from 'react-bootstrap/Button';
import Image from "react-bootstrap/Image";

export default function EmptyTileForBuilding({onClick, position}) {

    const handleOnClick = () => {
        onClick(position);
    };

    return (
        <Button onClick={handleOnClick} bsPrefix="building-btn">
            <Image src={"./buildings/buildable-building.png"} fluid/>
        </Button>
    )
}