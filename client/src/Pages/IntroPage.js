import React,{useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import {BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';

export default function IntroPage({parkName, handleChooseName}) {

    const [redirect, setRedirect] = useState(false);

    function handleNameChange(e) {
        handleChooseName(e.target.value)
    }

    function handleStartGame() {
        fetch(`http://localhost:8080/park/new/${parkName}`)
        .then(() => setRedirect(true));
    }

    function renderRedirect() {
       if(redirect){
           return <Redirect to="/game" />
        }
    }

    return (
       <div className="intro-page">
            {renderRedirect()}
            <div className="intro-page-content">
                <h1 className="intro-title">Welcome to dino business</h1>
                <Form.Label>Choose name of your park: </Form.Label>
                <Form.Control onChange={handleNameChange} placeholder="Type your name" id="intro-input"/>
                <Button variant="jurassik" onClick={handleStartGame} disabled={!parkName} >Start game</Button>
            </div>
       </div>
    )
}