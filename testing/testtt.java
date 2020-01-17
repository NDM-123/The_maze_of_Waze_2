package testing;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.nodeData;
import gui.graphGui;
import utils.Point3D;

public class testtt {

	public static void main(String[] args) {
		DGraph g1 = new DGraph();
		Point3D  g = new Point3D(Math.random() * 700 + 10, Math.random() * 500 + 100, 0);
		Point3D  g2 = new Point3D(Math.random() * 700 + 10, Math.random() * 500 + 100, 0);
		Point3D  g3 = new Point3D(Math.random() * 700 + 10, Math.random() * 500 + 100, 0);
		Point3D  g4 = new Point3D(Math.random() * 700 + 10, Math.random() * 500 + 100, 0);
		Point3D  g5 = new Point3D(Math.random() * 700 + 10, Math.random() * 500 + 100, 0);
		nodeData t1 = new nodeData(g,0,0.0,null,0);
		nodeData t2 = new nodeData(g2,1,0.0,null,0);
		nodeData t3 = new nodeData(g3,2,0.0 ,null,0);
		nodeData t4 = new nodeData(g4,3,0.0 ,null,0);
		nodeData t5 = new nodeData(g5,4 ,0.0,null,0);
		g1.addNode(t1);
		g1.addNode(t2);
		g1.addNode(t3);
		g1.addNode(t4);
		g1.addNode(t5);
		g1.connect(0, 1, 1); 
		g1.connect(1, 2, 2); 
		g1.connect(2, 3, 6); 
		g1.connect(3, 0, 4); 
		g1.connect(2, 4, 3); 
		g1.connect(4, 2, 5); 
		Graph_Algo G1 = new Graph_Algo(g1);
		System.out.println(G1.shortestPathDist(2, 4));
		
		graphGui G = new graphGui();
		G.buildGraph(g1);
		
	}

}
