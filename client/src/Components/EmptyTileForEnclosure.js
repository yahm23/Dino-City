import React from 'react';
import Button from 'react-bootstrap/Button';
import Image from "react-bootstrap/Image";

export default function EmptyTileForEnclosure({onClick, position}) {

    const handleOnClick = () => {
        onClick(position);
    };

    return (
        <Button onClick={handleOnClick} bsPrefix="building-btn">
            <Image src={"./buildings/buildable-enclosure.png"} fluid/>
        </Button>
    )
}