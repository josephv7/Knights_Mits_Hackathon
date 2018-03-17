const constant = require("../constants/constant");
const recommendations = require("../constants/recommendation");
const Recom = require("../models/Recom");

class RecController {
  static generate(data) {
    const recs = data.map(generic => {
      const url_name = generic.phenotype["url_name"];
      const summary = generic.summary;
      const scores = generic.scores;
      const score = scores.find(score => score.score === summary.score);
      const numberScore = score.score;
      const rec = recommendations[url_name][numberScore];
      return {
        trait: url_name,
        summary,
        rec
      };
    });
    Recom.insert(recs);
  }
}

module.exports = RecController;
