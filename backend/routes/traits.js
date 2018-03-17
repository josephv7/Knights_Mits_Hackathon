const router = require("express").Router();
const GenomeLink = require("../controllers/GenomeLink");

router.get("/api", GenomeLink.getTraits);
router.get("/:userId", GenomeLink.get);

module.exports = router;
