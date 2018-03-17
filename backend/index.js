const express = require("express");
const compression = require("compression");
const helmet = require("helmet");
const session = require("express-session");
const bodyParser = require("body-parser");
const dotenv = require("dotenv");
const path = require("path");
const logger = require("morgan");
const cors = require("cors");

const connectMongo = require("./config/MongoConnect");
const traits = require("./routes/traits");

dotenv.load({ path: ".env" });

const app = express();

app.set("port", process.env.PORT || 3000);
app.use(logger("dev"));
app.use(compression());
app.use(helmet());

app.use(bodyParser.json());
app.use(cors());

app.get("/", (req, res) => {
  res.json({ text: "Hello World" });
});

app.use("/traits", traits);

connectMongo();

app.listen(app.get("port"), () => {
  console.log(`App running on port ${app.get("port")}`);
});
