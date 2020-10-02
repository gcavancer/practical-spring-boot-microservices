import React, { Component } from "react";
import { connect } from "react-redux";
import {
  restartGame,
  registerAttempt,
  getStats,
  getLeaderBoard,
} from "./actions";

class Attempt {
  constructor(guess, guesses, original, average, alias) {
    this.guess = guess;
    this.guesses = guesses;
    this.game = {
      original: "",
      average: "",
    };
    this.game.original = original;
    this.game.average = average;
    this.user = {
      alias: "",
    };
    this.user.alias = alias;
  }
}

class Game extends Component {
  componentDidMount = () => {
    this.props.restartGame();
  };

  attempt = () => {
    const guesses = this.props.guesses + 1;
    this.props.registerAttempt(
      new Attempt(
        this.inp.value,
        guesses,
        this.props.original,
        this.props.average,
        this.props.alias
      ),
      false,
      null
    );
  };

  restart = () => {
    this.inp.value = "";
    this.props.restartGame();
  };

  getLeaderBoard = () => {
    this.props.getLeaderBoard();
  };

  getMedalImages = () => {
    let medals = this.props.medals;
    let firstIndexOfMedal = medals.findIndex((element) =>
      element.includes("MEDAL")
    );
    let occurencesOfLucky = medals.filter(function (item) {
      return item.includes("LUCKY");
    }).length;
    let medalImages;
    if (occurencesOfLucky > 0) {
      medalImages = (
        <span>
          <img
            src={process.env.PUBLIC_URL + `${medals[firstIndexOfMedal]}.png`}
            alt={medals[firstIndexOfMedal]}
            width="30"
          />
          <img
            src={process.env.PUBLIC_URL + `LUCKY_NUMBER.png`}
            alt="Lucky Number Badge"
            width="30"
          />{" "}
          x {occurencesOfLucky}
        </span>
      );
    } else {
      medalImages = (
        <span>
          <img
            src={process.env.PUBLIC_URL + `${medals[firstIndexOfMedal]}.png`}
            alt=""
            width="30"
          />
        </span>
      );
    }
    return medalImages;
  };

  render() {
    // Outcome bar.
    let guessOutcomePara = null;
    if (this.props.deviation < 0) {
      guessOutcomePara = (
        <span className="alert alert-warning mt-2">Your guess is higher</span>
      );
    } else if (this.props.deviation > 0) {
      guessOutcomePara = (
        <span className="alert alert-warning mt-2">Your guess is lower</span>
      );
    } else if (this.props.deviation === 0) {
      guessOutcomePara = (
        <span className="alert alert-success mt-2">Yes! Win</span>
      );
    }

    // Spinner.
    let loadingSpinner = null;
    if (this.props.loading) {
      loadingSpinner = (
        <img
          src={process.env.PUBLIC_URL + "/spinner.gif"}
          className="mt-2"
          style={{ width: "20px", height: "20px" }}
          alt="Loading..."
        />
      );
    } else {
      loadingSpinner = null;
    }

    // Singular/plural.
    const wins = this.props.wins;
    let winOutput;
    if (wins === 1) {
      winOutput = "win";
    } else {
      winOutput = "wins";
    }

    const userId = this.props.userId;

    return (
      <div className="container-fluid">
        <h3>Number Guess Game</h3>
        <form>
          <div className="form-row" style={{ marginLeft: "0px" }}>
            <div className="form-group">
              <label htmlFor="guessInput">Guess the # between 1 & 1000</label>
              <input
                type="number"
                className="form-control"
                id="guessInput"
                ref={(inp) => (this.inp = inp)}
              />
            </div>
          </div>
        </form>
        <button
          onClick={this.attempt}
          className="btn btn-primary btn-sm mr-2 mt-2"
        >
          Submit
        </button>
        <button
          onClick={this.restart}
          className="btn btn-warning btn-sm mr-2 mt-2"
        >
          Restart
        </button>
        {loadingSpinner}
        <div className="mt-3">
          <p>
            # of guesses:{" "}
            <span className="badge badge-secondary">{this.props.guesses}</span>
          </p>
          <p>
            Average # of guesses (all games):{" "}
            <span className="badge badge-secondary">{this.props.average}</span>
          </p>
          <h4>User Statistics</h4>
          <p>
            {this.props.alias} has{" "}
            <span className="badge badge-secondary">{wins}</span> {winOutput}
          </p>
          <p>{this.getMedalImages()}</p>
        </div>
        <p>
          {guessOutcomePara}
        </p>
        <h4>Leaderboard</h4>
        <button
          onClick={this.getLeaderBoard}
          className="btn btn-primary btn-sm mr-2 mt-2"
        >
          Refresh
        </button>
        <table id="leaderboard" className="table mt-2">
          <thead>
            <tr>
              <th>User ID</th>
              <th>Average</th>
            </tr>
          </thead>
          <tbody id="leaderboard-body">
            {this.props.leaderBoard.map((data, i) => {
              return (
                <tr key={data.userId}>
                  <td>
                    {this.props.userId === data.userId
                      ? `${data.userId} [${this.props.alias}]`
                      : data.userId}
                  </td>
                  <td>{(data.average).toFixed(1)}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    alias: state.gameState.alias,
    average: state.gameState.average,
    deviation: state.gameState.deviation,
    guess: state.gameState.guess,
    guesses: state.gameState.guesses,
    loading: state.gameState.loading,
    original: state.gameState.original,
    wins: state.gameState.wins,
    medals: state.gameState.medals,
    leaderBoard: state.gameState.leaderBoard,
    userId: state.gameState.userId,
  };
};

const mapDispatchToProps = {
  restartGame,
  registerAttempt,
  getStats,
  getLeaderBoard,
};

export default connect(mapStateToProps, mapDispatchToProps)(Game);
