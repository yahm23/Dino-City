import React, {useState} from 'react';
import GamePage from "./Pages/GamePage";
import IntroPage from "./Pages/IntroPage";
import {BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';

function App() {

  const [parkName, setParkName] = useState('');

   function handleChooseName(name) {
      setParkName(name);
      
    }
    
  return (
    <Router> 
      <React.Fragment>
        <Switch>
          <Route exact path="/" render={ () => <IntroPage parkName={parkName} handleChooseName={handleChooseName} />} />
          <Route path="/game" render={ () => <GamePage parkName={parkName} /> }/>
        </Switch>
      </React.Fragment>
    </Router>
  )

}

export default App;
