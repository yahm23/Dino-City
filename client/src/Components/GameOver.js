import React,{useState, useEffect} from 'react';

export default function GameOver({parkName}) {

    const [stats, setStats] = useState({money: 0, income:0, population: 0 });

    useEffect(() => {
        getStats();
    }, []) 

    function getStats() {
        if(parkName){
            fetch(`http://localhost:8080/park/stats/name/${parkName}`)
            .then(res => res.json())
            .then(data => setStats(data))
        }
    }
    
    return(
       <>
            <h1>Gave over</h1>
            <h2>Your {parkName} business went bankrupt..</h2>
            <p>You've finished the game having {stats.population} dinosaurs.</p>
       </>
    )
}