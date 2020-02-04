import React from 'react';
import DinosaursList from "./DinosaursList";

export default function EnclosureDetail({dinosaurs, enclosure, money, buyDinosaur}) {
    return (
            <div>
                {enclosure.size.size}
                <DinosaursList dinosaurs={dinosaurs} buyDinosaur={buyDinosaur}/>
            </div>
        )
}