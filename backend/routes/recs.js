const router = require("express").Router();
const RecController = require("../controllers/RecController");

router.get("/recommendation", RecController.generate);

module.exports = router;
