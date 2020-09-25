import React, { Component } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import createSagaMiddleware from 'redux-saga';
import Game from './components/game/Game';
import reducer from './rootReducer';
import rootSaga from './sagas';
import { composeWithDevTools } from 'redux-devtools-extension';

const sagaMiddleware = createSagaMiddleware();

const store = createStore(
  reducer,
  composeWithDevTools(applyMiddleware(sagaMiddleware)),
);

sagaMiddleware.run(rootSaga);

class App extends Component {
  render() {
    return (
      <div>
        <Provider store={store}>
          <Game />
        </Provider>
      </div>
    );
  }
}

export default App;
