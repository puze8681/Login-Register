var express = require('express');
var router = express.Router();
const rndString = require('randomstring');

/* GET home page. */
router.post('/register', function(req, res, next) {
  var user_id = req.body.user_id;
  var user_password = req.body.user_password;
  var user_name = req.body.user_name;
  var user_age = Number(req.body.user_age);

  if(user_id === undefined || user_password ===  undefined || user_name ===  undefined || user_age ===  undefined || user_id === "" || user_password === "" || user_name === "" || user_age === ""){
    return res.status(400).send("param missing");
  }

  var new_user = new Users({
    user_id: user_id,
    user_password: user_password,
    user_name: user_name,
    user_age: user_age,
    token: rndString.generate()
  });

  new_user.save(function(err, result){
    if(err) return res.status(500).send("DB err");
    return res.status(200).send(new_user);
  });
})

.post('/login', function(req, res, next) {
  var user_id = req.body.user_id;
  var user_password = req.body.user_password;

  if(user_id === undefined || user_password ===  undefined || user_id === "" || user_password === ""){
    return res.status(400).send("param missing");
  }

  Users.findOne({user_id: user_id, user_password: user_password}, function(err, users){
    if(err) return res.status(500).send("DB err");
    if(users)  return res.status(200).json({user_name: users.user_name, token: users.token});
    else return res.status(404).send("id or password not found");
  });
})

.post('/auto', function(req, res, next) {
  var token = req.body.token;

  Users.findOne({token: token}, function(err, users){
    if(err) return res.status(500).send("DB err");
    if(users)  return res.status(200).json({user_name: users.user_name, token: users.token});
    else return res.status(404).send("token not found");
  });
})



module.exports = router;
