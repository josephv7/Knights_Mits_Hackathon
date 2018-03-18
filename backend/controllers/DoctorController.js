const connectMongo = require("../config/MongoConnect");

class DoctorController {
  static setActive(req, res) {
    const docId = req.params.docId;
    const userId = parseInt(req.query.id);
    connectMongo().then(db => {
      const database = db.db("vita");
      const collection = database.collection("doctor");
      collection.updateOne({ docId }, { $set: { userId } }, { upsert: true });
      res.json({ text: "Update Success" });
    });
  }
  static checkActive(req, res) {
    const docId = req.params.id;
    connectMongo().then(db => {
      const database = db.db("vita");
      const collection = database.collection("doctor");
      collection.findOne({ docId: docId }, (error, result) => {
        if (error) throw error;
        if (result.userId !== null) {
          res.json({ status: true, userId: result.userId });
        } else {
          res.json({ status: false });
        }
      });
    });
  }
}

module.exports = DoctorController;
