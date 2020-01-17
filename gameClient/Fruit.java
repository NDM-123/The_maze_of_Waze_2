package gameClient;

import org.json.JSONException;
import org.json.JSONObject;

import dataStructure.DGraph;
import dataStructure.edgeData;
import utils.Point3D;

public class Fruit {
 private int type;
 private Point3D pos;	
 private int value;


private edgeData edge;
 
 public Fruit(String s, DGraph g) {
		try {
			JSONObject fruit = new JSONObject(s);//deserialize
			JSONObject fruit2 = fruit.getJSONObject("Fruit");
			String pi = fruit2.getString("pos"); //get location
			this.pos = new Point3D(pi); 
			this.type = fruit2.getInt("type");//get location int;
			this.value = fruit2.getInt("value");// get value
			this.edge = (edgeData) Game_Algo.getFruitEdge(s, g);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}  
}
 
 public int getType() {
	return type;
}

public Point3D getPos() {
	return pos;
}

public int getValue() {
	return value;
}
public edgeData getEdge() {
	return edge;
}


 
}
