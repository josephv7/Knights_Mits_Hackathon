const constant = require("../constants/constant");
const fetch = require("node-fetch");

class GenomeLink {
  static getTraits(req, res) {
    Promise.all(
      constant.SCOPE.split(" ").map(query => {
        return fetch(
          `https://genomelink.io/v1/reports/${query}/?population=european`,
          {
            headers: {
              Authorization: "Bearer GENOMELINKTEST001"
            }
          }
        ).then(res => res.json());
      })
    ).then(responses => {
      res.json(responses);
    });
  }
}

module.exports = GenomeLink;
