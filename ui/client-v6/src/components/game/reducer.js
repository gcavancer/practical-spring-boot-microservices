const initialState = {
  alias: 'gc',
  userId: '',
  medals: ['NONE'],
  average: '',
  deviation: '',
  original: '',
  guesses: 0,
  attempt: {},
  wins: '',
  loading: false,
  leaderBoard: []
};

export default function reducer(state = initialState, action) {
  switch (action.type) {
    case "RESTART_GAME":
      return {
        ...state,
        average: "",
        deviation: "",
        original: "",
        guesses: 0,
        loading: true,
      };
    case "RESTART_GAME_COMPLETED":
      return {
        ...state,
        attempt: {},
        average: action.payload.average,
        original: action.payload.original,
        message: "",
        loading: false,
      };
    case "REGISTER_ATTEMPT":
      return {
        ...state,
        attempt: action.payload,
      };
    case "REGISTER_ATTEMPT_STARTED":
      return {
        ...state,
        guesses: state.guesses + 1,
        loading: true,
      };
    case "REGISTER_ATTEMPT_COMPLETED":
      return {
        ...state,
        deviation: action.payload,
        loading: false,
      };
    case "REQUEST_FAILED":
      return {
        ...state,
        message: action.error,
        loading: false,
      };
    case "GET_STATS":
      return {
        ...state,
      };
    case "GET_STATS_STARTED":
      return {
        ...state,
      };
    case "GET_STATS_COMPLETED":
      return {
        ...state,
        userId: action.payload.userId,
        wins: action.payload.wins,
        medals: action.payload.medals
      };
    case "GET_LEADER_BOARD":
        return {
          ...state,
        };
    case "GET_LEADER_BOARD_STARTED":
        return {
          ...state,
        };
    case "GET_LEADER_BOARD_COMPLETED":
        return {
          ...state,
          leaderBoard: action.payload.data
        };
    default:
      return state;
  }
}
