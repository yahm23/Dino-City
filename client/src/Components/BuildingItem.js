import React from 'react'

export default function BuildingItem({money, building, buyBuilding}) {

    return(
        <>
        <p>{building.name}</p>
        <p>{building.price}</p>
        <p>{building.reputationMultiplier}</p>
        <p>{building.cost}</p>
        </>
    )
} 