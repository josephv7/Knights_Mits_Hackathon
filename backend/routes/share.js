const router = require("express").Router();
const DoctorController = require("../controllers/DoctorController");

router.get("/:docId", DoctorController.setActive);
router.get("/check/:id", DoctorController.checkActive);

module.exports = router;
