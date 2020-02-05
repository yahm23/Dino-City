import React, {useEffect, useState} from "react";
import DinosaursListItem from "./DinosaursListItem";

export default function DinosaursList({dinosaurs, money, threatLevel, size, buyDinosaur, setSumOfDinos, enclosureDiet}) {

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
            return <DinosaursListItem
                dinosaur={dinosaur}
                money={money}
                threatLevel={threatLevel}
                size={size}
                onBuyClick={buyDinosaur}
                key={dinosaur.name}
                setSumOfDinos={setSumOfDinos}
                enclosureDiet={enclosureDiet}
            />
        })
    };

    return (
        <div>
            <div>
                <h4>Herbivore</h4>
                <div className="dinosaur-list">
                    {dinosaursList(herbivors)}
                </div>
            </div>
            <div>
                <h4>Carnivores</h4>
                <div className="dinosaur-list">
                    {dinosaursList(carnivors)}
                </div>
            </div>
        </div>
    )
}