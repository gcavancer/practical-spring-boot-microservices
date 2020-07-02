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

export const registerAttempt = (attempt) => {
  return {
      type: types.REGISTER_ATTEMPT,
      payload: attempt
  };
}

export const registerAttemptStarted = () => {
  return {
      type: types.REGISTER_ATTEMPT_STARTED
  };
}

export const registerAttemptCompleted = (deviation) => {
  return {
      type: types.REGISTER_ATTEMPT_COMPLETED,
      payload: deviation
  };
}

export const requestFailed = (message) => {
  return {
    type: types.REQUEST_FAILED,
    payload: false,
    error: message
  };
};

export const getStats = (alias) => {
  return {
    type: types.GET_STATS,
    payload: alias
  };
}

export const getStatsStarted = () => {
  return {
    type: types.GET_STATS_STARTED
  };
}

export const getStatsCompleted = (wins) => {
  return {
    type: types.GET_STATS_COMPLETED,
    payload: wins
  };
}