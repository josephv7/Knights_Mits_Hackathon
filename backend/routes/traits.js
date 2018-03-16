const router = require("express").Router();
const GenomeLink = require("../controllers/GenomeLink");

router.get("/", GenomeLink.getTraits);

module.exports = router;
