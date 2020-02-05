import React from "react";
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";

export default function DinosaursListItem({dinosaur, money, threatLevel, size, onBuyClick, setSumOfDinos, enclosureDiet}) {
    const handleOnClick = () => {
        onBuyClick(dinosaur.name);  
    };

    const isDinosaurAvailable = () => {
        return dinosaur.price <= money &&
            dinosaur.threatLevel.threatLevel <= threatLevel &&
            dinosaur.size <= size &&
            setSumOfDinos() + dinosaur.size <= size &&
            isSameDiet()
    };

    const isSameDiet = () => {
        return enclosureDiet ? dinosaur.dietType.name === enclosureDiet : true;
    };


    return (
        <div className={isDinosaurAvailable() ? "dinosaur-item" : "dinosaur-item-disabled"}>
            <Image src={`./dinosaurs/${dinosaur.name}.png`} fluid/>
            <p className="bold">{dinosaur.name}</p>
            <p>SIZE: {dinosaur.size}</p>
            <p>THREAT: {dinosaur.threatLevel.name}</p>
            <p>PRICE: £{dinosaur.price}</p>
            <Button
                onClick={handleOnClick}
                variant="dark-jurassik"
                disabled={!isDinosaurAvailable()}>
                    Buy
            </Button>
        </div>
    )
}