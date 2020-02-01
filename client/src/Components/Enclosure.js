import React,{Component, useState} from 'react';
import Form from 'react-bootstrap/Form';

function Enclosure() {

    const [size, setSize] = useState({size: "SMALL", price:2000});
    const [security, setSecurity] = useState({security: "LOW", price: 1.2});


    const handleSizeChange = (e) => {
        setSize({size: e.target.id, price: e.target.value});
    }

    const handleSecurityChange = (e) => {
        setSecurity({security: e.target.id, price:e.target.value});
    }

    


   
        return(
            <>
                <h1>Build Enclosure</h1>
                <Form.Check type="radio" name="size" id="SMALL" value={2000} default onChange={handleSizeChange}/> Small<br/>
                <Form.Check type="radio" name="size" id="MEDIUM" value={5000} onChange={handleSizeChange}/> Medium<br/>
                <Form.Check type="radio" name="size" id="LARGE" value={12000} onChange={handleSizeChange}/> Large  

                <Form.Check type="radio" name="security" id="LOW" value={1.2} default onChange={handleSecurityChange}/> Low<br/>
                <Form.Check type="radio" name="security" id="MEDIUM" value="1.4" onChange={handleSecurityChange}/> Medium<br/>
                <Form.Check type="radio" name="security" id="HIGH" value="1.6" onChange={handleSecurityChange}/> High 
            

                <p>Total: €{size.price + (size.price * security.price)}</p>
            </>
        )
    
}

export default Enclosure;