const router = require("express").Router();
const RecController = require("../controllers/RecController");

router.get("/object/:object", RecController.recognise);
router.get("/:userId", RecController.get);

module.exports = router;
