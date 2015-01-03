from flask import Flask, request, jsonify, make_response, json
from flask.ext.sqlalchemy import SQLAlchemy
from datetime import datetime
from pymongo import MongoClient
from bson.json_util import dumps

app = Flask(__name__)
db = SQLAlchemy(app)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:@localhost/restapi'

client = MongoClient('localhost', 27017)
mongodb = client['test']
collection = mongodb['shirt']

########################################################
# MySQL Table object
########################################################
class RestAPI(db.Model):
  __tablename__ = 'shoe'
  shoeId = db.Column(db.Integer, primary_key = True)
  shoeName = db.Column(db.String(50))
  shoeQuantity = db.Column(db.Integer)
  createdBy = db.Column(db.String(50))
  timeStamp = db.Column(db.DateTime(50))
  
  def __init__(self, id, name, qty, createdby, timestamp):
    self.shoeId = id
    self.shoeName = name
    self.shoeQuantity = qty
    self.createdBy = createdby
    self.timeStamp = timestamp
    
########################################################
# GET Request to query the MySQL DB for a given shoeId
########################################################

@app.route('/shoe/<int:id>', methods=['GET'])
def shoes_get(id):
  if request.method == 'GET':
    result = RestAPI.query.filter_by(shoeId=id).first()
    if result is None:
	  return "ShoeID <" + str(id) + "> doesnot exist"
    json_result = {'shoe_id': result.shoeId,
                   'shoe_name': result.shoeName,
                   'shoe_quantity': result.shoeQuantity,
                   'created_by': result.createdBy,
                   'timestamp': result.timeStamp}

    return jsonify(items=json_result)
    
########################################################
# POST Request to insert shoe details in MySQL DB 
########################################################

@app.route("/shoes", methods=['POST'])
def shoes_post():
  if request.method == 'POST':
    id = int(request.json['shoeId'])
    name = request.json['shoeName']
    qty = int(request.json['shoeQuantity'])
    createdby = request.json['createdBy']
    tuple = RestAPI(id,name,qty,createdby,datetime.now())
    db.session.add(tuple)
    db.session.commit()
    return "OK"
    
########################################################
# PUT Request to update a given shoeId in MySQL DB 
########################################################

@app.route("/shoes/<int:id>", methods=['PUT'])
def shoes_put(id):
  if request.method == 'PUT':
    result = RestAPI.query.filter_by(shoeId=id).first()
    if result is None:
	  return "ShoeID <" + str(id) + "> doesnot exist"
    if 'shoeName' in request.json:
      result.shoeName = request.json['shoeName']
    if 'shoeQuantity' in request.json:
      result.shoeQuantity = int(request.json['shoeQuantity'])
    if 'createdBy' in request.json:
      result.createdBy =  request.json['createdBy']
    db.session.commit()
    return "OK"
    
########################################################
# DELETE Request to delete a given shoeId in MySQL DB 
########################################################

@app.route("/shoes/<int:id>", methods=['DELETE'])
def shoes_delete(id):
  if request.method == 'DELETE':
    result = RestAPI.query.filter_by(shoeId=id).first()
    if result is None:
	  return "ShoeID <" + str(id) + "> doesnot exist"
    db.session.delete(result)
    db.session.commit()
    return "OK"
    
########################################################
# GET Request to query the MongoDB for a given shirtId
########################################################

@app.route('/shirt/<int:id>', methods=['GET'])
def shirts_get(id):  
  result = collection.find_one({'shirtId': str(id)})
  if result is None:
	return "ShirtID <" + str(id) + "> doesnot exist"
  return dumps(result)
  
########################################################
# POST Request to insert a shirt document in MongoDB 
########################################################

@app.route("/shirts", methods=['POST'])
def shirts_post():
  if request.method == 'POST':
    shirtId = request.json['shirtId']
    shirtName = request.json['shirtName']
    shirtDescription = request.json['shirtDescription']
    shirtPrice = request.json['shirtPrice']
    createdBy = request.json['createdBy']
    collection.insert({"shirtId":shirtId,"shirtName":shirtName,"shirtDescription":shirtDescription,"shirtPrice":shirtPrice,"createdBy":createdBy,"timeStamp":datetime.utcnow()})
    return "OK"

########################################################
# PUT Request to update a given shirtId in MongoDB 
########################################################

@app.route("/shirts/<int:id>", methods=['PUT'])
def shirts_put(id):
  if request.method == 'PUT':
  
    result = collection.find_one({'shirtId': str(id)})
    if result is None:
	  return "ShirtID <" + str(id) + "> doesnot exist"
    shirtName = result['shirtName']
    shirtDescription = result['shirtDescription']
    shirtPrice = result['shirtPrice']
    createdBy = result['createdBy']
    
    if 'shirtName' in request.json:
      shirtName = request.json['shirtName']
    if 'shirtDescription' in request.json:
      shirtDescription = int(request.json['shirtDescription'])
    if 'shirtPrice' in request.json:
      shirtPrice = int(request.json['shirtPrice'])
    if 'createdBy' in request.json:
      createdBy =  request.json['createdBy']
      
    collection.update({"shirtId":str(id)},{"shirtId":str(id),"shirtName":shirtName,"shirtDescription":shirtDescription,"shirtPrice":shirtPrice,"createdBy":createdBy,"timeStamp":datetime.utcnow()})
    return "OK"  

########################################################
# DELETE Request to delete a given shirtId in MongoDB 
########################################################

@app.route("/shirts/<int:id>", methods=['DELETE'])
def shirts_delete(id):
  if request.method == 'DELETE':  
    result = collection.find_one({'shirtId': str(id)})
    if result is None:
	  return "ShirtID <" + str(id) + "> doesnot exist"    
    collection.remove({"shirtId":str(id)})
    return "OK"  

########################################################
    
if __name__ == '__main__':
  app.run(host='0.0.0.0', debug=True)