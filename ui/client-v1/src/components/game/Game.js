import React, { Component } from 'react';
import { connect } from 'react-redux';
import { restartGame, verifyGuess } from './actions';

class Game extends Component {
  componentDidMount = () => {
    this.props.restartGame();
  };

  verify = () => {
    this.props.verifyGuess(this.inp.value, false, null);
  };

  restart = () => {
    this.inp.value = '';
    this.props.restartGame();
  };

  render() {
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
          onClick={this.verify}
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
        </div>
        {guessOutcomePara}
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    average: state.gameState.average,
    deviation: state.gameState.deviation,
    guesses: state.gameState.guesses,
    loading: state.gameState.loading
  };
};

const mapDispatchToProps = {
  restartGame,
  verifyGuess,
};

export default connect(mapStateToProps, mapDispatchToProps)(Game);