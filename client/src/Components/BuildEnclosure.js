import React,{Component, useState} from 'react';
import Form from 'react-bootstrap/Form';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

function BuildEnclosure({money, buyEnclosure, enclosures}) {


    const [sizeSelected, setSize] = useState({size: "SMALL", price:3000});
    const [securitySelected, setSecurity] = useState({security: "LOW", price: 1.0});

    const handleSizeChange = (e) => {
        setSize({size: e.target.id, price: parseInt(e.target.value)});
    }

    const handleSecurityChange = (e) => {
        setSecurity({security: e.target.id, price: parseFloat(e.target.value)});   
    }

    function totalCost() {
        return (securitySelected.price * sizeSelected.price);
    }

    function handleBuy() {
        buyEnclosure(sizeSelected, securitySelected);
    }

    const enclosureSizeTypes = enclosures.types.map(size => {
        return <Form.Check type="radio" name="size" key={size.name} id={size.name} value={size.price} 
                    checked={sizeSelected.price === size.price } 
                    onChange={handleSizeChange} 
                    label={size.name + ` - £${size.price}`}
                 />
    })

    const enclosureSecurityTypes = enclosures.securityLevels.map(security => {
        return <Form.Check type="radio" name="security" key={security.name} id={security.name} value={security.priceMultiplier} 
                    checked={securitySelected.price === security.priceMultiplier } 
                    onChange={handleSecurityChange} 
                    label={security.name + ` +${parseInt((security.priceMultiplier - 1.0) * 100)}%`}
                />
    })

   
        return(
            <Container className="show-grid">
                <Row>
                    <h1>Build Enclosure</h1>
                </Row>
                <Row>
                    <Col>
                        <div className="size-panel">
                            {enclosureSizeTypes}
                        </div>
                    </Col>
                    <Col>
                        <div className="security-panel">
                            {enclosureSecurityTypes}
                        </div>
                    </Col>
                </Row>
                <Row>
                    <p>Total: £{totalCost()}</p>
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

export default BuildEnclosure;