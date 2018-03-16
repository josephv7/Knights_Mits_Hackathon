const fetch = require("node-fetch");

function genomeAPI() {
  return new Promise((resolve, reject) => {
    fetch("https://genomelink.io/v1/reports/eye-color?population=european", {
      headers: {
        Authorization: constant.AUTH
      }
    })
      .then(response => response.json())
      .then(response => resolve(response))
      .catch(error => reject(error));
  });
}

module.exports = genomeAPI;
