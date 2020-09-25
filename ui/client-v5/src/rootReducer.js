import { combineReducers } from 'redux'
import gameReducer from './components/game/reducer'

export default combineReducers({
  gameState: gameReducer
})
