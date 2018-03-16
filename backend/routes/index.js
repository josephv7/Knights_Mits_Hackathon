const router = require("express").Router();

router.use("/get-traits", require("./traits"));

module.exports = router;
