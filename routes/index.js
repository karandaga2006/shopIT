var express = require('express');
var mysql = require('mysql');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.render('index', { title: 'Express' });
});

/* GET Laptops page. */
router.get('/laptops', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({category:"Laptops"},function(e,docs){
        res.render('laptops', {
            "laptops" : docs
        });
    });
});

/* GET Laptop details*/
router.get('/laptops-:id', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({sku: req.params.id},function(e,docs){
        res.render('laptopDetail', {
            "laptop" : docs
        });
    });
});

/* GET Tablets page. */
router.get('/tablets', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({category:"Tablets"},function(e,docs){
        res.render('tablets', {
            "tablets" : docs
        });
    });
});

/* GET Tablet details*/
router.get('/tablets-:id', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({sku: req.params.id},function(e,docs){
        res.render('tabletDetail', {
            "tablet" : docs
        });
    });
});

/* GET Headphones page. */
router.get('/headphones', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({category:"Headphones"},function(e,docs){
        res.render('headphones', {
            "headphones" : docs
        });
    });
});

/* GET Headphone details*/
router.get('/headphones-:id', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({sku: req.params.id},function(e,docs){
        res.render('headphoneDetail', {
            "headphone" : docs
        });
    });
});

/* GET Consoles page. */
router.get('/consoles', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({category:"Consoles"},function(e,docs){
        res.render('consoles', {
            "consoles" : docs
        });
    });
});

/* GET Console details*/
router.get('/consoles-:id', function(req, res) {
    var db = req.db;
    var collection = db.get('inventory');
    collection.find({sku: req.params.id},function(e,docs){
        res.render('consoleDetail', {
            "console" : docs
        });
    });
});

module.exports = router;
