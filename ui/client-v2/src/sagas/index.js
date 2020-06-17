import { put, takeEvery, all, call, delay, select } from "redux-saga/effects";
import { apiRestartGame, apiWin } from "../api";
import {
  registerWin,
  registerWinStarted,
  registerWinCompleted,
  verifyGuessStarted,
  verifyGuessCompleted,
  restartGame,
  requestFailed,
  restartGameStarted,
  restartGameCompleted,
} from "../components/game/actions";

export const getWin = (state) => state.gameState.win;
export const getOriginal = (state) => state.gameState.original;

function* verifyGuessSaga(action) {
  yield put(verifyGuessStarted());
  const original = yield select(getOriginal);
  const deviation = original - action.payload;
  if (deviation === 0) {
    const win = yield select(getWin);
    yield put(registerWin(win));
  } else {
    yield put(verifyGuessCompleted());
  }
}

function* registerWinSaga(action) {
  try {
    yield put(registerWinStarted());
    yield call(apiWin, action.payload);
    yield delay(1000);
    yield put(registerWinCompleted());
    yield delay(1000);
    yield put(restartGame());
  } catch (e) {
    yield put(requestFailed("Error: " + e.message));
  }
}

function* restartGameSaga() {
  try {
    yield put(restartGameStarted());
    const response = yield call(apiRestartGame);
    const data = response.data;
    yield delay(1000);
    yield put(restartGameCompleted(data));
  } catch (e) {
    yield put(requestFailed("Error: " + e.message));
  } 
}

function* restartGameWatcherSaga() {
  yield takeEvery("RESTART_GAME", restartGameSaga);
}

function* registerWinWatcherSaga() {
  yield takeEvery("REGISTER_WIN", registerWinSaga);
}

function* verifyGuessWatcherSaga() {
  yield takeEvery("VERIFY_GUESS", verifyGuessSaga);
}

export default function* rootSaga() {
  yield all([
    restartGameWatcherSaga(),
    registerWinWatcherSaga(),
    verifyGuessWatcherSaga(),
  ]);
}
