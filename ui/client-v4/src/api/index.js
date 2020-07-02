import axios from "axios";

const API_BASE_URL = 'http://localhost:8081/number-guess-service/app';

export const apiRestartGame = () => {
    return axios({
        method: 'get',
        url: API_BASE_URL + '/service/game'
    })
}

export const apiRegisterAttempt = (attempt) => {
  let headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  };
  return axios({
      method: 'post',
      url: API_BASE_URL + '/results',
      data: attempt,
      headers
  });
}

export const apiGetStatistics = (alias) => {
  return axios({
    method: "get",
    url: API_BASE_URL + '/results?alias=' + alias
  })
}