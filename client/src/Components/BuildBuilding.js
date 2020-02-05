import React from 'react';
import Form from 'react-bootstrap/Form';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import BuildingItem from '../Components/BuildingItem';

export default function BuildBuilding({money, buildings, buyBuilding}) {

    const buildingTypes = buildings.map((building) => {
        return <BuildingItem money={money} building={building} buyBuilding={buyBuilding} key={building.name}/>
        
    })

    return (
        <Container className="show-grid">
            <Row>
                <h1>Build building</h1>
            </Row>
            <Row>
                <Col>
                    <div className="size-panel">
                        {buildingTypes}
                    </div>
                </Col>
            </Row>
        </Container>
    )
}