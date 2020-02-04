import React, {useEffect, useState} from 'react';
import DinosaursList from "./DinosaursList";
import Button from "react-bootstrap/Button";

export default function EnclosureDetail({dinosaurs, enclosure, money, buyDinosaur, sellDinosaur,
                                            enclosureTypes, updateEnclosureSize, updateEnclosureSecurity}) {

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
            return <div key={dinosaur.id}>
                    <p>{dinosaur.species.name}</p>
                    <Button onClick={() => handleSellDino(dinosaur.id)}>Sell</Button>
                </div>
    });

    const displayUpgradeSize = () => {
        const nextIndex = getCurrentSizeIndex() + 1;
        if (nextIndex <= (enclosureTypes.types.length - 1)) {
            const nextSize = enclosureTypes.types[nextIndex];
            const cost = Math.round((nextSize.price-enclosure.size.price)*1.1);
            if (money >= cost) {
                return "UPGRADE SIZE " + nextSize.name + " £"  + cost;
            }
        }
        return "";
    };

    function handleUpgradeSize() {
        updateEnclosureSize(enclosureTypes.types[getCurrentSizeIndex() + 1].name)
    }

    const displayUpgradeSecurity = () => {
        const nextIndex = getCurrentSecurityIndex() + 1;
        if (nextIndex <= (enclosureTypes.securityLevels.length - 1)) {
            const nextSecurityLevel = enclosureTypes.securityLevels[nextIndex];
            const cost = Math.round((nextSecurityLevel.priceMultiplier - enclosure.securityLevel.priceMultiplier + 1)*600);
            if (money >= cost) {
                return "UPGRADE SECURITY " + nextSecurityLevel.name + " £"  + cost;
            }
            return "NOT ENOUGH MONEY";
        }
        return "FULLY UPGRADED";
    };
    
    function handleUpgradeSecurity() {
        updateEnclosureSecurity(enclosureTypes.securityLevels[getCurrentSecurityIndex() + 1].name)
    }

    function canUpgrade(displayFunction) {
        return displayFunction === "FULLY UPGRADED" || displayFunction === "NOT ENOUGH MONEY";
    }

    function getCurrentSizeIndex() {
        const size = enclosureTypes.types.find(type => type.name === enclosure.size.name);
        return enclosureTypes.types.indexOf(size);
    }

    function getCurrentSecurityIndex() {
        const security = enclosureTypes.securityLevels.find(type => type.name === enclosure.securityLevel.name);
        return enclosureTypes.securityLevels.indexOf(security);
    }

    return (
        
            <div>
                <p>Capacity: {setSumOfDinos()}/{enclosure.size.size}</p>
                <p>Security Level: {enclosure.securityLevel.threatLevel.name}</p>
                { enclosure.dinosaurs[0] && <p>This enclosure is for: {enclosure.dinosaurs[0].species.dietType.name}</p>}
                <Button
                    onClick={handleUpgradeSize}
                    disabled={canUpgrade(displayUpgradeSize())}>
                    {displayUpgradeSize()}
                </Button>
                <Button
                    onClick={handleUpgradeSecurity}
                    disabled={canUpgrade(displayUpgradeSecurity())}>
                    {displayUpgradeSecurity()}
                </Button>
                
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