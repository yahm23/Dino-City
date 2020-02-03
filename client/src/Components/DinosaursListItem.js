import React from "react";
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";

export default function DinosaursListItem({dinosaur, onBuyClick}) {
    const handleOnClick = () => {
        onBuyClick(dinosaur.name);
    };

    return (
        <div className="dinosaur-item">
            <Image src={`./dinosaurs/${dinosaur.name}.jpg`} fluid/>
            <p>NAME: {dinosaur.name}</p>
            <p>SIZE: {dinosaur.size}</p>
            <p>THREAT: {dinosaur.threatLevel.name}</p>
            <p>PRICE: £{dinosaur.price}</p>
            <Button>Buy</Button>
        </div>
    )
}