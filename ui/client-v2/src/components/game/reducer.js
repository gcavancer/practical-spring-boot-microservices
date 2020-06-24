import * as types from '../../constants/ActionTypes';

const initialState = {
  average: '',
  deviation: '',
  original: '',
  guesses: 0,
  win: {},
  message: '',
  loading: false
};

export default function reducer(state = initialState, action) {
  switch (action.type) {
    case types.RESTART_GAME:
      return {
        ...state,
        average: "",
        deviation: "",
        original: '',
        guesses: 0,
        loading: true,
      };
    case types.RESTART_GAME_COMPLETED:
      return {
        ...state,
        average: action.payload.average,
        original: action.payload.original,
        loading: false,
      };
    case types.VERIFY_GUESS:
      return {
        ...state,
        deviation: state.original - action.payload,
        guesses: state.guesses + 1,
        win: Object.assign({}, { guesses: state.guesses + 1 }),
      };
    case types.VERIFY_GUESS_STARTED:
      return {
        ...state,
        loading: action.payload,
      };
    case types.VERIFY_GUESS_COMPLETED:
      return {
        ...state,
        loading: action.payload,
      };
    case types.REGISTER_WIN:
      return {
        ...state,
        loading: true,
      };
    case types.REGISTER_WIN_STARTED:
      return {
        ...state,
        loading: true,
      };
    case types.REGISTER_WIN_COMPLETED:
      return {
        ...state,
        win: {},
        loading: false,
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
