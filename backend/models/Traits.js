const connectMongo = require("../config/MongoConnect");
const MongoClient = require("mongodb").MongoClient;

class Traits {
  static insert(data) {
    connectMongo().then(db => {
      const database = db.db("vita");
      const collection = database.collection("traits");
      collection.insertMany(data);
    });
  }
  static get() {
    return new Promise((resolve, reject) => {
      connectMongo()
        .then(db => {
          const database = db.db("vita");
          const collection = database.collection("traits");
          collection.find({}).toArray((error, result) => {
            if (error) throw error;
            resolve(result);
          });
        })
        .catch(error => reject(error));
    });
  }
  static generateRecommendations() {}
}

module.exports = Traits;
