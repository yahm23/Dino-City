import React from 'react';
import Button from 'react-bootstrap/Button';
import Image from "react-bootstrap/Image";

export default function EnclosureTile({enclosure, onClick, position}) {
    const buildingImg = () => {
        const dino = enclosure.dinosaurs ? enclosure.dinosaurs[0] : undefined;
        if (dino && dino.species.dietType.name === "CARNIVORE") {
            return "./buildings/carnivore-enclosure.png"
        } else if (dino && dino.species.dietType.name === "HERBIVORE"){
            return "./buildings/herbivore-enclosure.png"
        }
        return "./buildings/empty-enclosure.png";
    };

    const handleOnClick = () => {
        onClick(position);
    };

    return (
        <Button onClick={handleOnClick} bsPrefix="building-btn"><Image src={buildingImg()} fluid/></Button>
    )
}