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
    case "RESTART_GAME":
      return {
        ...state,
        average: "",
        deviation: "",
        // OLD: original: Math.floor(Math.random() * 1000 + 1),
        original: '',
        guesses: 0,
        loading: true,
      };
    case "RESTART_GAME_COMPLETED":
      return {
        ...state,
        // OLD: average: action.payload,
        average: action.payload.average,
        // ADD:
        original: action.payload.original,
        loading: false,
      };
    case "VERIFY_GUESS":
      return {
        ...state,
        deviation: state.original - action.payload,
        guesses: state.guesses + 1,
        win: Object.assign({}, { guesses: state.guesses + 1 }),
      };
    case "VERIFY_GUESS_STARTED":
      return {
        ...state,
        loading: action.payload,
      };
    case "VERIFY_GUESS_COMPLETED":
      return {
        ...state,
        loading: action.payload,
      };
    case "REGISTER_WIN_STARTED":
      return {
        ...state,
        loading: true,
      };
    case "REGISTER_WIN_COMPLETED":
      return {
        ...state,
        win: {},
        loading: false,
      };
    case "REGISTER_WIN":
      return {
        ...state,
        loading: true,
      };
    case "REQUEST_FAILED":
      return {
        ...state,
        message: action.error,
        loading: false,
      };
    default:
      return state;
  }
}
