package gameClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;



public class Scenario {
	 DGraph gr;
     Graph_Algo G; 
	game_service game;
	 ArrayList<Robot> robot = new ArrayList<Robot>();
	 ArrayList<Fruit> fruit = new ArrayList<Fruit>();


	public Scenario(int scenario_num ) {
		this.game = Game_Server.getServer(scenario_num);
		String g = game.getGraph();	
		this.gr = new DGraph();
		this.gr.init(g);
		G = new Graph_Algo(gr);
		String info = game.toString();
		
		Iterator<String> f_iter = game.getFruits().iterator();
		while(f_iter.hasNext()) {
			Fruit fr = new Fruit(f_iter.next(),this.gr);
			fruit.add(fr);
		}
		JSONObject line;
		try {
			line = new JSONObject(info);
			JSONObject ttt = line.getJSONObject("GameServer");
			int robotNum = ttt.getInt("robots");
			Game_Algo.addRobotNearFruit(robotNum,fruit,game);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		List<String> robots = game.getRobots();
		for(int i=0;i<robots.size();i++) { //add robot list
			String robot_json = robots.get(i);
			Robot rob = new Robot(robot_json);
			this.robot.add(rob);

		}


	

	}


}
