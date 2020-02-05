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
        return <Col  key={size.name}>
                
                <img width="20%" src={`./sizeIcons/${size.name}.png`.toLowerCase()} ></img>

                <Form.Check type="radio" name="size"  id={size.name} value={size.price}
                    checked={sizeSelected.price === size.price }
                    onChange={handleSizeChange}
                    label={size.name + ` - £${size.price}`}
                 /></Col>
    })

    const enclosureSecurityTypes = enclosures.securityLevels.map(security => {
        return <Col  key={security.name} >


            <img width="20%" src={`./securityIcons/${security.name}.png`.toLowerCase()} ></img>
            <Form.Check type="radio" name="security"  id={security.name} value={security.priceMultiplier}
                      checked={securitySelected.price === security.priceMultiplier }
                      onChange={handleSecurityChange}
                      label={security.name + ` +${parseInt((security.priceMultiplier - 1.0) * 100)}%`}/>
          </Col>
    })


        return(
            <Container className="show-grid">

                    <Row padding="20px">
                    <h4 className="bold">SIZE</h4> 
                    </Row>
                    <Row>
                         {enclosureSizeTypes}
                    </Row>

                    <div className="line-breaker"> </div>
                    <Row>
                    <h4 className="bold">SECURITY</h4> 
                    </Row>
                    <Row>
                      {enclosureSecurityTypes}
                    </Row>

               

                <Row>
                    <p>Total: £{totalCost()}</p>
                </Row>
                <Row>
                    <Button
                    variant={money >= totalCost() ? "dark-jurassik" : "secondary"}
                    disabled={totalCost() > money}
                    onClick={handleBuy} >
                        Buy
                    </Button>
                </Row>
            </Container>
        )

}

export default BuildEnclosure;
