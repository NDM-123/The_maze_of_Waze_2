package gameClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.node_data;
import oop_dataStructure.oop_graph;

public class RunGame   {
	private  Graph_Algo G;
	 static Scenario scenario;



	public RunGame(int scenario_num){
		scenario = new Scenario(scenario_num);
		G = new Graph_Algo(scenario.gr);



		Thread startGame = new Thread( new Runnable() {
			@Override
			public void run() {
				scenario.game.startGame();
				int count = 0;
				while(scenario.game.isRunning()) { 
					long t = scenario.game.timeToEnd();
					if(count%10==0) {
						System.out.println( "time to end:"+(t/1000));
						}
					moveRobots(scenario.game, scenario.gr);
					try {
					
						Thread.sleep(100);
						count++;
					
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String results = scenario.game.toString();
				System.out.println("Game Over: "+results);

			}
		});
		startGame.start();

	}





	private static void moveRobots(game_service game, DGraph gg) {
		List<String> log = game.move();
		
		if(log!=null) {
			long t = game.timeToEnd();
			for(int i=0;i<log.size();i++) {

				
				String robot_json = log.get(i);
				try {
					JSONObject line = new JSONObject(robot_json);
					JSONObject ttt = line.getJSONObject("Robot");
					int rid = ttt.getInt("id");
					int src = ttt.getInt("src");
					int dest = ttt.getInt("dest");

					
					if(dest==-1) {
						dest = Game_Algo.nextNode(scenario, src);
						System.out.println(ttt);
						game.chooseNextEdge(rid, dest);
						//System.out.println("Turn to node: "+dest);
						
					}
				} 
				catch (JSONException e) {e.printStackTrace();}
			}
		}
	}	


}


