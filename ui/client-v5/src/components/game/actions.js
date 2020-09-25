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

export const restartGameCompleted = (data) => {
  return {
    type: 'RESTART_GAME_COMPLETED',
    payload: data
  };
};

export const registerAttempt = (attempt) => {
  return {
      type: 'REGISTER_ATTEMPT',
      payload: attempt
  };
}

export const registerAttemptStarted = () => {
  return {
      type: 'REGISTER_ATTEMPT_STARTED'
  };
}

export const registerAttemptCompleted = (deviation) => {
  return {
      type: 'REGISTER_ATTEMPT_COMPLETED',
      payload: deviation
  };
}

export const requestFailed = (message) => {
  return {
    type: 'REQUEST_FAILED',
    payload: false,
    error: message
  };
};

export const getStats = (alias) => {
  return {
    type: 'GET_STATS',
    payload: alias
  };
}

export const getStatsStarted = () => {
  return {
    type: 'GET_STATS_STARTED'
  };
}

export const getStatsCompleted = (wins, medals, userId) => {
  return {
    type: 'GET_STATS_COMPLETED',
    payload: { wins, medals, userId }
  };
}

export const getLeaderBoard = () => {
  return {
    type: 'GET_LEADER_BOARD'
  };
}

export const getLeaderBoardStarted = () => {
  return {
    type: 'GET_LEADER_BOARD_STARTED'
  };
}

export const getLeaderBoardCompleted = (data) => {
  return {
    type: 'GET_LEADER_BOARD_COMPLETED',
    payload: { data }
  };
}