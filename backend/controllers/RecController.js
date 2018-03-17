const constant = require("../constants/constant");
const recommendations = require("../constants/recommendation");
const connectMongo = require("../config/MongoConnect");
const Recom = require("../models/Recom");

class RecController {
  static generate(data, id) {
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
        rec,
        userId: id
      };
    });
    Recom.insert(recs);
  }
  static get(req, res) {
    const userId = req.params.userId;
    return new Promise((resolve, reject) => {
      connectMongo()
        .then(db => {
          const database = db.db("vita");
          const collection = database.collection("recommendations");
          collection.find({ userId }).toArray((error, result) => {
            if (error) throw error;
            res.json(result);
            resolve(result);
          });
        })
        .catch(error => reject(error));
    });
  }
  static recognise(req, res) {
    const obj = req.params.object;
    const id = req.query.id;
    const expression = new RegExp(obj, "i");
    connectMongo().then(db => {
      const database = db.db("vita");
      const collection = database.collection("recommendations");
      collection
        .find({ userId: id, "rec.food": expression })
        .toArray((error, result) => {
          if (error) throw error;
          res.json(result);
        })
        .catch(error => console.error(error));
    });
  }
}

module.exports = RecController;
