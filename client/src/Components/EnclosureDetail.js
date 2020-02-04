import React, {useEffect, useState} from 'react';
import DinosaursList from "./DinosaursList";

export default function EnclosureDetail({dinosaurs, enclosure, money, buyDinosaur, sellDinosaur}) {

    function setSumOfDinos() {
        let sum = 0;
        enclosure.dinosaurs.forEach(dinosaur => {
            sum += dinosaur.species.size;
        });
        return sum;
    }

    function handleSellDino(id)  {
        sellDinosaur(id);
    }

    const listOfDinosInEnclosure = enclosure.dinosaurs.map(dinosaur => {
            return <div>
                    <p>{dinosaur.species.name}</p>
                    <button onClick={() => handleSellDino(dinosaurs.id)}>Sell</button>
                </div>
        })
        

    return (
        
            <div>
                <p>Capacity: {setSumOfDinos()}/{enclosure.size.size}</p>
                <p>Security Level: {enclosure.securityLevel.threatLevel.name}</p>
                { enclosure.dinosaurs[0] && <p>This enclosure is for: {enclosure.dinosaurs[0].species.dietType.name}</p>}
                
                {listOfDinosInEnclosure}

                <DinosaursList
                    dinosaurs={dinosaurs}
                    money={money}
                    threatLevel={enclosure.securityLevel.threatLevel.threatLevel}
                    size={enclosure.size.size}
                    buyDinosaur={buyDinosaur}
                    setSumOfDinos={setSumOfDinos}
                    />
            </div>
        )
}