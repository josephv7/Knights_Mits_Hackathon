const constant = require("../constants/constant");
const fetch = require("node-fetch");

const Traits = require("../models/Traits");
const RecController = require("./RecController");

class GenomeLink {
  static get(req, res) {
    Traits.get().then(response => {
      RecController.generate(response);
      res.json(response);
    });
  }
  static getTraits(req, res) {
    Promise.all(
      constant.SCOPE.split(" ").map(query => {
        return fetch(
          `https://genomelink.io/v1/reports/${query}/?population=european`,
          {
            headers: {
              Authorization: constant.AUTH
            }
          }
        ).then(res => res.json());
      })
    ).then(responses => {
      Traits.insert(responses);
      res.json(responses);
    });
  }
}

module.exports = GenomeLink;
