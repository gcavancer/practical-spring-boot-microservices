import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/number-guess-service/app/service';

export const apiGetAverageGuesses = () => {
    return axios({
        method: 'get',
        url: API_BASE_URL + '/average'
    })
}

export const apiWin = win => {
    let headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    };
    return axios({
        method: 'post',
        url: API_BASE_URL,
        data: win,
        headers
    });
}