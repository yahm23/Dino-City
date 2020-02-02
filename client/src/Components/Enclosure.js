import React,{Component, useState} from 'react';
import Form from 'react-bootstrap/Form';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

function Enclosure({money, buyEnclosure}) {


    const [size, setSize] = useState({size: "SMALL", price:2000});
    const [security, setSecurity] = useState({security: "LOW", price: 1.2});

    const handleSizeChange = (e) => {
        setSize({size: e.target.id, price: parseInt(e.target.value)});
    }

    const handleSecurityChange = (e) => {
        setSecurity({security: e.target.id, price: parseFloat(e.target.value)});   
    }

    function totalCost() {
        return (security.price * size.price);
    }

    function handleBuy() {
        buyEnclosure(size, security);
    }

   
        return(
            <Container className="show-grid">
                <Row>
                    <h1>Build Enclosure</h1>
                </Row>
                <Row>
                    <Col>
                        <div className="size-panel">
                            <Form.Check type="radio" name="size" id="SMALL" value={2000} 
                                checked={size.price === 2000 } 
                                onChange={handleSizeChange} 
                                label="Small"
                            />
                            <Form.Check type="radio" name="size" id="MEDIUM" value={5000} 
                                checked={size.price === 5000 } 
                                onChange={handleSizeChange} 
                                label="Medium"
                            />
                            <Form.Check type="radio" name="size" id="LARGE" value={12000} 
                                checked={size.price === 12000 } 
                                onChange={handleSizeChange} 
                                label="Large"
                            />
                        </div>
                    </Col>
                    <Col>
                        <div className="security-panel">
                            <Form.Check type="radio" name="security" id="LOW" value={1.2} 
                                checked={security.price === 1.2 } 
                                onChange={handleSecurityChange} 
                                label="Low"
                            />
                            <Form.Check type="radio" name="security" id="MEDIUM" value={1.4} 
                                checked={security.price === 1.4 } 
                                onChange={handleSecurityChange} 
                                label="Medium"
                            />
                            <Form.Check type="radio" name="security" id="HIGH" value={1.6} 
                                checked={security.price === 1.6 } 
                                onChange={handleSecurityChange} 
                                label="High"
                            />
                        </div>
                    </Col>
                </Row>
                <Row>
                    <p>Total: €{totalCost()}</p>
                </Row>
                <Row>
                    <Button 
                    variant={money >= totalCost() ? "success" : "secondary"} 
                    disabled={totalCost() > money} 
                    onClick={handleBuy} >
                        Buy
                    </Button>
                </Row>
            </Container>
        )
    
}

export default Enclosure;