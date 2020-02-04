import React, {useEffect} from 'react';
import DinosaursList from "./DinosaursList";

export default function EnclosureDetail({dinosaurs, enclosure, money, size, buyDinosaur}) {
    return (
            <div>
                {enclosure.size.size}
                <DinosaursList
                    dinosaurs={dinosaurs}
                    money={money}
                    threatLevel={enclosure.securityLevel.threatLevel.threatLevel}
                    size={enclosure.size.size}
                    buyDinosaur={buyDinosaur}/>
            </div>
        )
}