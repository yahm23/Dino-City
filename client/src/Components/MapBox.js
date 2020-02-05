import React from 'react';
import Container from 'react-bootstrap/Container';

export default function MapBox({children}) {
        return(
            <Container bsPrefix="game-box">
                {children}
            </Container>
        )
}