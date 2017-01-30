var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  Users.find(),function(err, users){
    if(err) return res.status(500).send("DB err").json({success: "500"});
    if(users) return res. status(200).json({success: "200", user_id: users.user_id, user_password: users.user_password, user_name: users.user_name, user_age: users.user_age,token: users.token});
    else return res.status(404).send("user not found").json({success: "404"});
  }
})

.delete('/delete', function(req, res, next) {
  var token = req.body.token;

  Users.remove({token: token}, function(err, users){
    if(err) return res.status(500).send("DB err").json({success: "500"});
    if(users)  return res.status(200).json({success: "200", user_name: users.user_name, token: users.token});
    else return res.status(404).send("token not found").json(success: "404");
  });
})

module.exports = router;
