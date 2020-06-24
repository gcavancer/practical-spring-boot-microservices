import * as types from '../../constants/ActionTypes';

export const restartGame = () => {
  return {
    type: types.RESTART_GAME
  };
};

export const restartGameStarted = () => {
  return {
    type: types.RESTART_GAME_STARTED
  };
};

export const restartGameCompleted = (data) => {
  return {
    type: types.RESTART_GAME_COMPLETED,
    payload: data
  };
};

export const registerWin = (win) => {
  return {
    type: types.REGISTER_WIN,
    payload: win,
  };
};

export const registerWinStarted = () => {
  return {
    type: types.REGISTER_WIN_STARTED
  };
};

export const registerWinCompleted = () => {
  return {
    type: types.REGISTER_WIN_COMPLETED
  };
};

export const verifyGuess = (guess) => {
  return {
    type: types.VERIFY_GUESS,
    payload: guess,
  };
};

export const verifyGuessStarted = () => {
  return {
    type: types.VERIFY_GUESS_STARTED,
    payload: true,
  };
};

export const verifyGuessCompleted = () => {
  return {
    type: types.VERIFY_GUESS_COMPLETED,
    payload: false,
  };
};

export const requestFailed = (message) => {
  return {
    type: types.REQUEST_FAILED,
    payload: false,
    error: message
  };
};