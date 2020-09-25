import { put, takeEvery, all, call, delay, select } from "redux-saga/effects";
import { apiRegisterAttempt, apiRestartGame, apiGetStatistics, apiGetMedals, apiGetLeaderBoard } from "../api";
import {
  registerAttemptStarted,
  registerAttemptCompleted,
  restartGame,
  requestFailed,
  restartGameStarted,
  restartGameCompleted,
  getStats,
  getStatsStarted,
  getStatsCompleted,
  getLeaderBoardStarted,
  getLeaderBoardCompleted
} from "../components/game/actions";

export const getAlias = (state) => state.gameState.alias;

function* registerAttemptSaga(action) {
  try {
    yield put(registerAttemptStarted());
    const response = yield call(apiRegisterAttempt, action.payload);
    const deviation = response.data.deviation;
    yield put(registerAttemptCompleted(deviation));
    yield delay(2000);  // Needed to see win in view.
    if(deviation === 0) {
      yield put(restartGame());
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
    const userId = data[0].user.id;
    // yield delay(1000);
    const data2 = yield call(apiGetMedals, userId);
    yield put(getStatsCompleted(len, data2.data.medals, userId));
    // yield put(getStatsCompleted(len, userId));
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  }
}

function* getLeaderBoardSaga() {
  try {
    yield put(getLeaderBoardStarted());
    const response = yield call(apiGetLeaderBoard);
    const data = response.data;
    yield put(getLeaderBoardCompleted(data));
  } catch (e) {
    yield put(requestFailed('Error: ' + e.message));
  }
}

function* restartGameWatcher() {
  yield takeEvery('RESTART_GAME', restartGameSaga);
}

function* registerAttemptWatcher() {
  yield takeEvery('REGISTER_ATTEMPT', registerAttemptSaga);
}

function* getStatsWatcher() {
  yield takeEvery('GET_STATS', getStatsSaga);
}

function* getLeaderBoardWatcher() {
  yield takeEvery('GET_LEADER_BOARD', getLeaderBoardSaga);
}

export default function* rootSaga() {
  yield all([
    restartGameWatcher(),
    registerAttemptWatcher(),
    getStatsWatcher(),
    getLeaderBoardWatcher()
  ]);
}
