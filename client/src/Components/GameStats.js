import React from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export default function GameStats({stats}) {

    return(
       <Container className="game-stats">
           <Row>
               <Col>BALANCE: £{stats.money}</Col>
               <Col>INCOME: £{stats.income}</Col>
               <Col>EXPENSES: £{stats.cost}</Col>
               <Col>RATING: {stats.rating}</Col>
               <Col>DINOSAURS: {stats.population}</Col>
           </Row>
       </Container>
    )
} 