import { put, takeEvery, all, call, select, delay } from 'redux-saga/effects';
import { apiGetAverageGuesses, apiWin } from '../api';
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
} from '../components/game/actions';

export const getWin = state => state.gameState.win;
export const getOriginal = state => state.gameState.original;

export function* verifyGuessSaga(action) {
  try {
    yield put(verifyGuessStarted());
    const original = yield select(getOriginal);
    const deviation = original - action.payload;
    if (deviation === 0) {
      const win = yield select(getWin);
      yield put(registerWin(win));
    } else {
      yield put(verifyGuessCompleted());
    }
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  }
}

export function* registerWinSaga(action) {
  try {
    yield put(registerWinStarted());
    yield call(apiWin, action.payload);
    yield delay(1000);
    yield put(registerWinCompleted());
    yield delay(1000);
    yield put(restartGame());
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  }
}

export function* restartGameSaga() {
  try {
    yield put(restartGameStarted());
    const response = yield call(apiGetAverageGuesses);
    const average = response.data;
    yield delay(1000);
    yield put(restartGameCompleted(average));
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  } 
}

function* restartGameWatcher() {
  yield takeEvery('RESTART_GAME', restartGameSaga);
}

function* registerWinWatcher() {
  yield takeEvery('REGISTER_WIN', registerWinSaga);
}

function* verifyGuessWatcher() {
  yield takeEvery('VERIFY_GUESS', verifyGuessSaga);
}

export default function* rootSaga() {
  yield all([
    restartGameWatcher(),
    registerWinWatcher(),
    verifyGuessWatcher(),
  ]);
}