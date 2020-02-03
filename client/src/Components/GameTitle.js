import React from "react";

export default function GameTitle ({parkName}) {
    return (
        <div className="game-title">
            <h1>{parkName}</h1>
        </div>
    )
}