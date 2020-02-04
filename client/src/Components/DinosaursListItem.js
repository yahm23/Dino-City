import React from "react";
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";

export default function DinosaursListItem({dinosaur, money, threatLevel, size, onBuyClick, setSumOfDinos}) {
    const handleOnClick = () => {
        onBuyClick(dinosaur.name);  
    };

    const isDinosaurAvailable = () => {
        return dinosaur.price <= money &&
            dinosaur.threatLevel.threatLevel <= threatLevel &&
            dinosaur.size <= size &&
            setSumOfDinos() + dinosaur.size <= size
    };


    return (
        <div className={isDinosaurAvailable() ? "dinosaur-item" : "dinosaur-item-disabled"}>
            <Image src={`./dinosaurs/${dinosaur.name}.jpg`} fluid/>
            <p>NAME: {dinosaur.name}</p>
            <p>SIZE: {dinosaur.size}</p>
            <p>THREAT: {dinosaur.threatLevel.name}</p>
            <p>PRICE: £{dinosaur.price}</p>
            <Button
                onClick={handleOnClick}
                disabled={!isDinosaurAvailable()}>
                    Buy
            </Button>
        </div>
    )
}