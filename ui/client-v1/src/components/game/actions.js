export const restartGame = () => {
  return {
    type: 'RESTART_GAME'
  };
};

export const restartGameStarted = () => {
  return {
    type: 'RESTART_GAME_STARTED'
  };
};

export const restartGameCompleted = (average) => {
  return {
    type: 'RESTART_GAME_COMPLETED',
    payload: average
  };
};

export const registerWin = (win) => {
  return {
    type: 'REGISTER_WIN',
    payload: win,
  };
};

export const registerWinStarted = () => {
  return {
    type: 'REGISTER_WIN_STARTED'
  };
};

export const registerWinCompleted = () => {
  return {
    type: 'REGISTER_WIN_COMPLETED'
  };
};

export const verifyGuess = (guess) => {
  return {
    type: 'VERIFY_GUESS',
    payload: guess,
  };
};

export const verifyGuessStarted = () => {
  return {
    type: 'VERIFY_GUESS_STARTED',
    payload: true,
  };
};

export const verifyGuessCompleted = () => {
  return {
    type: 'VERIFY_GUESS_COMPLETED',
    payload: false,
  };
};

export const requestFailed = (message) => {
  return {
    type: 'REQUEST_FAILED',
    payload: false,
    error: message
  };
};