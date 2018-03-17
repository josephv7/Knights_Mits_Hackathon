const router = require("express").Router();
const GenomeLink = require("../controllers/GenomeLink");

router.get("/", GenomeLink.get);
router.get("/api", GenomeLink.getTraits);

module.exports = router;
