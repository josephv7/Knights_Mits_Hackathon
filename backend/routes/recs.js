const router = require("express").Router();
const RecController = require("../controllers/RecController");

router.get("/", RecController.get);

module.exports = router;
