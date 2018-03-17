const MongoClient = require("mongodb").MongoClient;
const constant = require("../constants/constant");

function connectMongo() {
  return MongoClient.connect(constant.DATABASE);
}

module.exports = connectMongo;
