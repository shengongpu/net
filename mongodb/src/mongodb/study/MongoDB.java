package mongodb.study;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/*
 * mongodb的增删改查
 */
public class MongoDB {
	@Test
	public void create() throws Exception{
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("test");
		DBObject options = new BasicDBObject();
		options.put("张三", 23);
		options.put("李四", 24);
		options.put("王五", 25);
		db.createCollection("student", options );
		System.out.println(db.getCollection("student"));
	}
	@Test
	public void find() throws UnknownHostException{
		Mongo mongo = new Mongo("localhost",27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("student");
		DBCursor cursor = collection.find();
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			System.out.println(object.toString());
		}
	}
	@Test
	public void add() throws UnknownHostException{
		Mongo mongo = new Mongo("localhost",27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("student");
		System.out.println(collection);
		//insert
		DBObject arr = new BasicDBObject();
		arr.put("马六", 26);
		collection.insert(arr );
		DBCollection collection2 = db.getCollection("student");
		System.out.println(db.getCollection("student"));
	}
}
