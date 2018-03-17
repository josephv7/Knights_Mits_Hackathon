const connectMongo = require("../config/MongoConnect");

class Recom {
  static get() {
    return new Promise((resolve, reject) => {
      connectMongo()
        .then(db => {
          const database = db.db("vita");
          const collection = database.collection("recommendations");
          collection.find({}).toArray((error, result) => {
            if (error) throw error;
            resolve(result);
          });
        })
        .catch(error => reject(error));
    });
  }
  static insert(data) {
    connectMongo().then(db => {
      const database = db.db("vita");
      const collection = database.collection("recommendations");
      collection.insertMany(data);
    });
  }
}

module.exports = Recom;
