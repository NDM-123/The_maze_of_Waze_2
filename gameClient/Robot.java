package gameClient;

import org.json.JSONException;
import org.json.JSONObject;

import utils.Point3D;

public class Robot {

	private int id;
	 private int src;	
	 private int dest;
	 private Point3D pos;	
	 
	 
	
	public Robot(String s) {
			try {
				JSONObject Robot = new JSONObject(s);//deserialize
				JSONObject Robot2 = Robot.getJSONObject("Robot");
				String pi = Robot2.getString("pos"); //get location
				this.pos = new Point3D(pi); 
				this.id = Robot2.getInt("id");//get id;
				this.src = Robot2.getInt("src");// get src
				this.dest = Robot2.getInt("dest");// get dest
			} 
			catch (JSONException e) {
				e.printStackTrace();
			}  
			
			
	}
	public int getSrc() {
		return src;
	}
	public void setSrc(int src) {
		this.src = src;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	public int getId() {
		return id;
	}
	public Point3D getPos() {
			return pos;
		}
}
