import React, {useEffect, useState} from "react";
import DinosaursListItem from "./DinosaursListItem";

export default function DinosaursList({dinosaurs, buyDinosaur}) {

    const [herbivors, setHerbivors] = useState([]);
    const [carnivors, setCarnivors] = useState([]);

    useEffect(() => {
        setHerbivors(filterDinosaurAndOrder("HERBIVORE"));
        setCarnivors(filterDinosaurAndOrder("CARNIVORE"));
    }, []);

    const filterDinosaurAndOrder = (diet) => {
        return dinosaurs
            .filter(dinosaur => dinosaur.dietType.name === diet)
            .sort((prevDino, actualDino) => prevDino.price - actualDino.price)
    };

    const dinosaursList = (dinoList) => {
        return dinoList.map(dinosaur => {
            return <DinosaursListItem dinosaur={dinosaur} onBuyClick={buyDinosaur}/>
        })
    };

    return (
        <div>
            <div>
                <h3>Herbivore</h3>
                <div className="dinosaur-list">
                    {dinosaursList(herbivors)}
                </div>
            </div>
            <div>
                <h3>Carnivores</h3>
                <div className="dinosaur-list">
                    {dinosaursList(carnivors)}
                </div>
            </div>
        </div>
    )
}