import React from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export default function GameStats({stats}) {

    return(
       <Container className="game-stats">
           <Row>
               <Col>MONEY: £{stats.money}</Col>
               <Col>INCOME: £{stats.income}</Col>
               <Col>COSTS: £{stats.cost}</Col>
               <Col>RATING: {stats.rating}</Col>
               <Col>POPULATION: {stats.population}</Col>
           </Row>
       </Container>
    )
} 