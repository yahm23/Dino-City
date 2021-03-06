import React, {useEffect, useState} from 'react';
import DinosaursList from "./DinosaursList";
import Button from "react-bootstrap/Button";
import Image from "react-bootstrap/Image";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

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
            return <div key={dinosaur.id} className="present-dinosaur">
                <div className="present-dinosaur-item">•  {dinosaur.species.name}</div>
                    <Button variant="dark-jurassik" onClick={() => handleSellDino(dinosaur.id)}>Sell</Button>
                </div>
    });

    const displayUpgradeSize = () => {
        const nextIndex = getCurrentSizeIndex() + 1;
        if (nextIndex <= (enclosureTypes.types.length - 1)) {
            const nextSize = enclosureTypes.types[nextIndex];
            const cost = Math.round((nextSize.price-enclosure.size.price)*1.1);
            if (money >= cost) {
                return "£"  + cost;
            }
            return " £"  + cost;
        }
        return "£0";
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
                return "£"  + cost;
            }
            return " £"  + cost;
        }
        return "£0";
    };
    
    function handleUpgradeSecurity() {
        updateEnclosureSecurity(enclosureTypes.securityLevels[getCurrentSecurityIndex() + 1].name)
    }

    function canUpgrade(displayFunction) {
        return displayFunction === "£0" || displayFunction.startsWith(" ");
    }

    function getCurrentSizeIndex() {
        const size = enclosureTypes.types.find(type => type.name === enclosure.size.name);
        return enclosureTypes.types.indexOf(size);
    }

    function getCurrentSecurityIndex() {
        const security = enclosureTypes.securityLevels.find(type => type.name === enclosure.securityLevel.name);
        return enclosureTypes.securityLevels.indexOf(security);
    }

    function getCurrentEnclosureDiet() {
        return enclosure.dinosaurs[0] ? enclosure.dinosaurs[0].species.dietType.name : "";
    }

    return (
        
        <div className="enclosure-details">
            <Row>
                <Col md={5}>
                    <div className="enclosure-details-content">
                        <div className="enclosure-details-informations">
                            <h5 className="bold">DETAILS</h5>
                            <p>Capacity: {setSumOfDinos()}/{enclosure.size.size}</p>
                            <p>Size: {enclosure.size.name}</p>
                            <p>Security Level: {enclosure.securityLevel.threatLevel.name}</p>
                            { enclosure.dinosaurs[0] && <p>This enclosure is for: {enclosure.dinosaurs[0].species.dietType.name}</p>}
                            <p className="bold">Upgrades:</p>
                            <Button
                                onClick={handleUpgradeSize}
                                disabled={canUpgrade(displayUpgradeSize())}
                                variant="dark-jurassik"
                            >
                                <img src="./buildings/size-upgrade.png" className="icon-button"/>
                                {displayUpgradeSize()}
                            </Button>
                            <Button
                                onClick={handleUpgradeSecurity}
                                disabled={canUpgrade(displayUpgradeSecurity())}
                                variant="dark-jurassik"
                            >
                                <img src="./buildings/security-upgrade.png" className="icon-button"/>
                                {displayUpgradeSecurity()}
                            </Button>
                        </div>
                        <div className="line-breaker"> </div>
                        <div className="actual-dinosaur-list">
                            <h5 className="bold">PRESENT DINOSAURS</h5>
                            {listOfDinosInEnclosure}
                        </div>

                    </div>
                </Col>
                <Col md={7}>
                    <div className="add-dinosaur-list">
                        <h5 className="bold">BUY DINOSAURS</h5>
                        <DinosaursList
                            dinosaurs={dinosaurs}
                            money={money}
                            threatLevel={enclosure.securityLevel.threatLevel.threatLevel}
                            size={enclosure.size.size}
                            buyDinosaur={buyDinosaur}
                            setSumOfDinos={setSumOfDinos}
                            enclosureDiet={getCurrentEnclosureDiet()}
                        />
                    </div>
                </Col>
                </Row>
            </div>
        )
}