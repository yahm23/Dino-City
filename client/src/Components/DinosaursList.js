import React, {useEffect, useState} from "react";
import DinosaursListItem from "./DinosaursListItem";

export default function DinosaursList({dinosaurs}) {

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
            return <DinosaursListItem dinosaur={dinosaur}/>
        })
    };

    return (
        <div>
            <div>
                <h3>Herbivore</h3>
                {dinosaursList(herbivors)}
            </div>
            <div>
                <h3>Carnivores</h3>
                {dinosaursList(carnivors)}
            </div>
        </div>
    )
}