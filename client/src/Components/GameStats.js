import React from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export default function GameStats({stats}) {

    return(
       <Container className="game-stats">
           <Row>
               <Col md={2}>Money: {stats.money}</Col>
               <Col md={2}>Income: {stats.income}</Col>
               <Col md={2}>Population: {stats.population}</Col>
           </Row>
       </Container>
    )
} 