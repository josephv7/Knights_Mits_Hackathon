const constant = require("../constants/constant");
const fetch = require("node-fetch");

class GenomeLink {
  static getTraits(req, res) {
    fetch(`https://genomelink.io/v1/?population=european`, {
      headers: {
        Authorization: constant.AUTH
      }
    })
      .then(response => response.json())
      .then(response => res.send(response))
      .catch(error => console.log(error));
  }
}

module.exports = GenomeLink;
