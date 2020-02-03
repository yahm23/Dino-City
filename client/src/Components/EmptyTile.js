import React from 'react';
import Button from 'react-bootstrap/Button';
import Image from "react-bootstrap/Image";

export default function EmptyTile({onClick, position}) {

    const handleOnClick = () => {
        onClick(position);
    };

    return (
        <Button onClick={handleOnClick} bsPrefix="building-btn"><Image src={"./buildings/empty-building.png"} fluid/></Button>
    )
}