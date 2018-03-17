const connectMongo = require("../config/MongoConnect");

class Recom {
  static get() {}
  static insert(data) {
    connectMongo().then(db => {
      const database = db.db("vita");
      const collection = database.collection("recommendations");
      collection.insertMany(data);
    });
  }
}

module.exports = Recom;
