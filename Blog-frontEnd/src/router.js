import React from 'react';
import { Router, Route, Switch, Redirect } from 'dva/router';
import IndexPage from './routes/IndexPage';

function RouterConfig({ history }) {
  console.log("history",history);
  
  return (
    <Router history={history}>
      <Switch>
        <Route path="/" component={IndexPage} />
        {/* <Redirect path="/" exact to="/" /> */}
        {/* <Redirect path="*" exact to="/home" /> */} 
      </Switch>
    </Router>
  );
}

export default RouterConfig;
