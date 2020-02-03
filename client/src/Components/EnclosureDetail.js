import React from 'react';
import DinosaursList from "./DinosaursList";

export default function EnclosureDetail({dinosaurs, getEnclosure}) {
    
    const [enclosure, setEnclosure] = useState({});

    setEnclosure(getEnclosure);

    return (
           { enclosure.size.size}
           <DinosaursList dinosaurs={dinosaurs} />
        )
}