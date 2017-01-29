var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  Users.find(),function(err, users){
    if(err) return res.status(500).send("DB err");
    if(users) return res. status(200).json(users);
    else return res.status(404).send("user not found");
  }
})

.delete('/delete', function(req, res, next) {
  var token = req.body.token;

  Users.remove({token: token}, function(err, users){
    if(err) return res.status(500).send("DB err");
    if(users)  return res.status(200).json({success: users.success, user_name: users.user_name, token: users.token});
    else return res.status(404).send("token not found");
  });
})

module.exports = router;
