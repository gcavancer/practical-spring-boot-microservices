import * as types from '../constants/ActionTypes';
import { put, takeEvery, all, call, delay } from "redux-saga/effects";
import { apiRegisterAttempt, apiRestartGame } from "../api";
import {
  registerAttemptStarted,
  registerAttemptCompleted,
  restartGame,
  requestFailed,
  restartGameStarted,
  restartGameCompleted,
} from "../components/game/actions";

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
    yield delay(1000);
    yield put(restartGameCompleted(data));
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

export default function* rootSaga() {
  yield all([
    restartGameWatcher(),
    registerAttemptWatcher(),
  ]);
}
