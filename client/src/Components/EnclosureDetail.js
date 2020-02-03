import React from 'react';
import DinosaursList from "./DinosaursList";

export default function EnclosureDetail({dinosaurs}) {

    return (
           <DinosaursList dinosaurs={dinosaurs} />
        )
}