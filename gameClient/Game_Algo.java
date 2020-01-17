package gameClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import oop_dataStructure.oop_edge_data;
import oop_dataStructure.oop_graph;
import utils.Point3D;

public class Game_Algo {


	public static final double EPS = 0.0001;


	public static void addRobotNearFruit(int robot, ArrayList<Fruit> fruit, game_service game) {
		for (int i = 0; i < robot; i++) {
			game.addRobot(fruit.get(i%fruit.size()).getEdge().getSrc());
		}
	}





	public static edge_data getFruitEdge(String f,DGraph g) throws JSONException {
		JSONObject fruit = new JSONObject(f); //deserialize
		JSONObject fruit2 = fruit.getJSONObject("Fruit");
		String pi = fruit2.getString("pos"); //get location
		Point3D  Pi = new Point3D(pi); 
		int type = fruit2.getInt("type");//get location int;
		Iterator<node_data> n = g.getV().iterator();
		//traversal all edges and checks if the the fruit is on edge  
		while(n.hasNext()) {
			Iterator<edge_data> e = g.getE(n.next().getKey()).iterator();
			while(e.hasNext()) {
				edge_data t = e.next();
				double disAB = g.getNode(t.getSrc()).getLocation().distance2D(Pi);  // distance from src to fruit
				double disBC = Pi.distance2D( g.getNode(t.getDest()).getLocation());  // distance from fruit to dest
				double disAC = g.getNode(t.getSrc()).getLocation().distance2D( g.getNode(t.getDest()).getLocation());    // distance from src to dest              
				//if is banana
				if(type==-1) { 
					if(disAB + disBC >= disAC-EPS && disAB + disBC <=disAC+EPS&&t.getSrc()-t.getDest()>0) {
						return t;
					}
					//if is apple	
				}else if(type==1) { 
					if(disAB + disBC >= disAC-EPS && disAB + disBC <=disAC+EPS&&t.getSrc()-t.getDest()<0) {
						return t;
					}
				}
			}
		}
		return null;
	}

	public static List<Integer> nextNode2(DGraph g, int src) {
		game_service game = Game_Server.getServer(2);
		List<Integer> n = new ArrayList<Integer>();
		n.add(src);
		Iterator<String> f_iter = game.getFruits().iterator();
		while(f_iter.hasNext()) {
			game.move();
			String f = f_iter.next();
			try {
				edge_data t = getFruitEdge(f,g);
				n.add(g.getNode(t.getSrc()).getKey());
				n.add(g.getNode(t.getDest()).getKey());
				System.out.println(g.getNode(t.getSrc()).getKey()+"-->"+g.getNode(t.getDest()).getKey());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}	
		return n;
	}
	public static int nextNode(Scenario scenario, int src) {
		int ans = -1;
		int dist = 0;
		int dist2 = 0;
		double min = -1;
		edge_data tmin = null;
		double candidate = 0;
		Iterator<String> f_iter = scenario.game.getFruits().iterator();
		while(f_iter.hasNext()) {
			String f = f_iter.next();	
			try {
				edge_data t = getFruitEdge(f,scenario.gr);
				if(min==-1) {
					min	= scenario.G.shortestPathDist(src,scenario.gr.getNode(t.getSrc()).getKey());
					dist = scenario.gr.getNode(t.getSrc()).getKey();
					tmin = t;
				}
				else {
					candidate =  scenario.G.shortestPathDist(src,scenario.gr.getNode(t.getSrc()).getKey());
					dist2 = scenario.gr.getNode(t.getSrc()).getKey();
				if(candidate<min) {
					min =  candidate;
					dist = dist2;
					tmin = t;
				}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		List<node_data> n = new ArrayList<node_data>();
        n =  scenario.G.shortestPath(src,dist);
        if(n!=null&&n.size()>1) {
        	ans = n.get(1).getKey();	
        }
        if(n!=null&&n.size()==1) {
        	ans = tmin.getDest();
        }

		return ans;
	}



}
