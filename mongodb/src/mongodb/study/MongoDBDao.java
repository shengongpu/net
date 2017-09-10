package mongodb.study;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoDBDao {
	
	@Test
	public void add() throws UnknownHostException{
		//连接mongodb服务
		Mongo mongo=new Mongo("localhost",27017);
		//获取数据库
		DB db=mongo.getDB("test");
		//获取集合
		DBCollection collection=db.getCollection("person");
		
		//被操作的对象
		BasicDBObject arr=new BasicDBObject();
		arr.put("name", "李观南");
		arr.put("age", 18);
		
		collection.insert(arr);
		//关闭资源
		mongo.close();
		
	}
	@Test
	public void del() throws UnknownHostException{
		
		Mongo mongo=new Mongo("localhost",27017);
		DB db=mongo.getDB("test");
		DBCollection collection=db.getCollection("person");
		
		//{}
		DBObject dbObject=new BasicDBObject();
		//删除，dbObject为删除条件对象
		collection.remove(dbObject);
			
	}
	@Test
	public void update() throws UnknownHostException{
		Mongo mongo=new Mongo("localhost",27017);
		DB db=mongo.getDB("test");
		DBCollection collection=db.getCollection("person");
		//{ "_id" : { "$oid" : "543f83027ae90916ed05a5bc"} , "name" : "李观南" , "age" : 18}
		
		BasicDBObject query = new BasicDBObject("_id",new ObjectId("543f83027ae90916ed05a5bc"));
		
		BasicDBObject dbObject=(BasicDBObject) collection.findOne(query);
		
		dbObject.put("name", "李世民");
		
		dbObject.put("age", 10);
		
		collection.update(query,dbObject);
		
//		collection.update(q, o);
		
	}
	@Test
	public void find() throws UnknownHostException{
		Mongo mongo=new Mongo("localhost",27017);
		DB db=mongo.getDB("test");
		DBCollection collection=db.getCollection("person");
		
		DBObject condtion=new BasicDBObject();
		condtion.put("name", "李世民");
		
		
//		DBObject keys=new BasicDBObject();
//		
//		//db.prson.find({name:"李冠男"},{name:1})
//		keys.put("name", 1);
		
		//db.person.find({age:{$gt:10}})
//	-------------------------------------------------------------------	
		//代表是一个{}
		//Dbobject dbobject=new BasicDBObject();
		
		//{}
		//Dbobject dbobject1=new BasicDBObject();
		
//		dbobject1.put{"$gt",10}
		//{$gt:10}
		
//		dbobject1.put("age",dbobject1)
		
		//{age:{$gt:10}}
		
//-------------------------------------------------------------		
		
		
		DBCursor cursor=collection.find(condtion);
		
		while(cursor.hasNext()){
			DBObject dbObject=cursor.next();
			System.out.println(dbObject.toString());
		}
		
		
	}

}
