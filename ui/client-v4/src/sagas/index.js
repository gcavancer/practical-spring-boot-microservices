import * as types from '../constants/ActionTypes';
import { put, takeEvery, all, call, delay, select } from "redux-saga/effects";
import { apiRegisterAttempt, apiRestartGame, apiGetStatistics } from "../api";
import {
  registerAttemptStarted,
  registerAttemptCompleted,
  restartGame,
  requestFailed,
  restartGameStarted,
  restartGameCompleted,
  getStats,
  getStatsStarted,
  getStatsCompleted
} from "../components/game/actions";

export const getAlias = (state) => state.gameState.alias;

function* registerAttemptSaga(action) {
  try {
    yield put(registerAttemptStarted());
    const response = yield call(apiRegisterAttempt, action.payload);
    const deviation = response.data.deviation;
    yield put(registerAttemptCompleted(deviation));
    yield delay(2000);
    if(deviation === 0) {
      yield put(restartGame());
    } else {
      yield put(registerAttemptCompleted());
    }
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  }    
}

function* restartGameSaga() {
  try {
    yield put(restartGameStarted());
    const response = yield call(apiRestartGame);
    const data = response.data;
    const alias = yield select(getAlias);
    yield put(getStats(alias));
    yield delay(1000);
    yield put(restartGameCompleted(data));
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  } 
}

function* getStatsSaga(action) {
  try {
    yield put(getStatsStarted());
    const response = yield call(apiGetStatistics, action.payload);
    const data = response.data;
    const len = data.length;
    yield put(getStatsCompleted(len));
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  }
}

function* restartGameWatcher() {
  yield takeEvery(types.RESTART_GAME, restartGameSaga);
}

function* registerAttemptWatcher() {
  yield takeEvery(types.REGISTER_ATTEMPT, registerAttemptSaga);
}

function* getStatsWatcher() {
  yield takeEvery(types.GET_STATS, getStatsSaga);
}

export default function* rootSaga() {
  yield all([
    restartGameWatcher(),
    registerAttemptWatcher(),
    getStatsWatcher()
  ]);
}