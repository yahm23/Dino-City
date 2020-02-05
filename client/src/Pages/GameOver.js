import React,{useState, useEffect} from 'react';
import {Redirect} from "react-router-dom";
import Button from "react-bootstrap/Button";

export default function GameOver({parkName}) {

    const [stats, setStats] = useState({money: 0, income:0, population: 0 });
    const [restart, setRestart] = useState(false);

    useEffect(() => {
        getStats();
    }, []) 

    function getStats() {
        if(parkName){
            fetch(`http://localhost:8080/park/stats/name/${parkName}`)
                .then(res => res.json())
                .then(data => setStats(data))
                .then(() => {
                    fetch(`http://localhost:8080/park/delete/name/${parkName}`, {method: "DELETE"})
                })
        }
    }

    function initializeNewGame() {
        return <Redirect to="/"/>
    }
    
    return(
       <>
           <div className="intro-page">
               <div className="intro-page-content">
                   <h1 className="intro-title">Game Over!</h1>

                   <h2>Your {parkName} business went bankrupt.</h2>
                   <p>You've finished the game having {stats.population} dinosaurs.</p>
                   <Button variant="dark-jurassik" onClick={() => setRestart(true)}>Play Again</Button>
                   {restart && initializeNewGame()}
               </div>
           </div>
       </>
    )
}