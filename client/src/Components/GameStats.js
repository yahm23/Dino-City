import React from 'react';

export default function GameStats({stats}) {

    return(
       <> 
        <p>Money: </p> {stats.money}
        <p>Income: </p> {stats.income}
        <p>Population: </p> {stats.population}
       </>
    )
} 