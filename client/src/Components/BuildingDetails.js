import React from 'react'

export default function BuildingDetails({building, sellBuilding}) {

    return (
        <p>{building.buildingType.name}</p>
    )
}