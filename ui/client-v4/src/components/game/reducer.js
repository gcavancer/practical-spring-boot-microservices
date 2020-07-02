import * as types from '../../constants/ActionTypes';

const initialState = {
  alias: 'gc',
  average: '',
  deviation: '',
  original: '',
  guesses: 0,
  attempt: {},
  wins: '',
  loading: false,
};

export default function reducer(state = initialState, action) {
  switch (action.type) {
    case types.RESTART_GAME:
      return {
        ...state,
        average: "",
        deviation: "",
        original: "",
        guesses: 0,
        loading: true,
      };
    case types.RESTART_GAME_COMPLETED:
      return {
        ...state,
        attempt: {},
        average: action.payload.average,
        original: action.payload.original,
        message: "",
        loading: false,
      };
    case types.REGISTER_ATTEMPT:
      return {
        ...state,
        attempt: action.payload,
      };
    case types.REGISTER_ATTEMPT_STARTED:
      return {
        ...state,
        guesses: state.guesses + 1,
        loading: true,
      };
    case types.REGISTER_ATTEMPT_COMPLETED:
      return {
        ...state,
        deviation: action.payload,
        loading: false,
      };
    case types.GET_STATS:
      return {
        ...state,
      };
    case types.GET_STATS_STARTED:
      return {
        ...state,
      };
    case types.GET_STATS_COMPLETED:
      return {
        ...state,
        wins: action.payload
      };
    case types.REQUEST_FAILED:
      return {
        ...state,
        message: action.error,
        loading: false,
      };
    default:
      return state;
  }
}
