package gameClient;


import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

/**
 * This class save the game in kml file
 */
public class KML_Logger {
    static StringBuilder Kfile = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<kml xmlns=\"http://earth.google.com/kml/2.2\">\n<Document>\n<name>The maze of Waze 2</name>");
    private static int scenarioInt;
    static RunGame rg = new RunGame(scenarioInt);


    /**
     * This is aMethod that can be clled fron anywhere and can save the data into a KML file
     */
    public static boolean KML_Save(Scenario s) {
        try {

            /**
             * Restarting the data needed to the KML file
             */
            scenarioInt = s.num;
            List<String> robots = s.game.getRobots();
            List<String> fruits = s.game.getFruits();
            graph gra = s.gr;
            Graph_Algo ga = new Graph_Algo();
            ga.init(gra);
            Date date = new Date();
            DateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hms = new SimpleDateFormat("HH:mm:ss");
            String year = ymd.format(date);
            String hour = hms.format(date);
            String fDate = year + "T" + hour + "Z";
            /**
             * Building the graph to the KML Language
             */
            for (node_data nd : gra.getV()) {
                Kfile.append("<Placemark>\n<description>\nLocation num:" + nd.getKey() + "\n</description>\n<TimeStamp>\n<when>" + fDate + "</when>\n</TimeStamp>\n<Point>\n<coordinates> " + nd.getLocation().x() + "," + nd.getLocation().y() + "\n</coordinates>\n</Point>\n</Placemark>\n");
                for (edge_data ed : gra.getE(nd.getKey())) {
                    Point3D psrc = gra.getNode(ed.getSrc()).getLocation();
                    Point3D pdest = gra.getNode(ed.getDest()).getLocation();
                    Kfile.append("\n" +
                            "<Placemark> \n" +
                            " <LineString>\n" +
                            "  <coordinates>" +
                            psrc.x() + "," + psrc.y() + ",0\t"
                            + pdest.x() + "," + pdest.y() + ",0" +
                            "  </coordinates>\n" +
                            " </LineString>\n" + "<Style>\n<LineStyle>\n <color>#ff0000ff</color> \n<width>5</width> \n</LineStyle> \n</Style>\n</Placemark>\n");
                }

            }
/**
 * Saving the robots and fruits to the KML
 */
            while (rg.scenario.game.isRunning()) {

                robots = rg.scenario.game.getRobots();
                fruits = rg.scenario.game.getFruits();


                Iterator it = robots.iterator();
                while (it.hasNext()) {
                    Robot r = new Robot((String) it.next());
                    Kfile.append("<Placemark>\n<description> Robot id:" + r.getId() + "\n</description>\n<TimeSpan>\n<begin>" + fDate + "</begin>\n<end>" + fDate + "</end>\n</TimeSpan>\n<Point>\n<coordinates>" + r.getPos().x() + "," + r.getPos().y() + "</coordinates>\n</Point>\n");
                    Kfile.append("<Style>\n<IconStyle>\n<scale>0.5</scale>\n<Icon>\n<href>rob.png</href>\n <refreshInterval>0.5</refreshInterval>\n<viewRefreshTime>0.5</viewRefreshTime>\n<viewBoundScale>0.5</viewBoundScale>\n</Icon>\n</IconStyle>\n</Style>\n</Placemark>\n");
                }
                Iterator it2 = fruits.iterator();
                while (it2.hasNext()) {
                    Fruit f = new Fruit((String) it2.next());
                    if (f.getType() > 0) {             //apple
                        Kfile.append("<Placemark>\n<description> Apple value:" + f.getValue() + "\n</description> \n<TimeStamp>\n<Start> " + fDate + "</Start>\n</TimeStamp>\n<Point>\n<coordinates>" + f.getPos().x() + "," + f.getPos().y() + "</coordinates>\n</Point>\n");
                        Kfile.append("<Style>\n<IconStyle>\n<scale>0.5</scale>\n<Icon>\n<href>apple2.png</href>\n <refreshInterval>0.5</refreshInterval>\n<viewRefreshTime>0.5</viewRefreshTime>\n<viewBoundScale>0.5</viewBoundScale>\n</Icon>\n</IconStyle>\n</Style>\n</Placemark>\n");
                    } else {                           //banana
                        Kfile.append("<Placemark>\n<description> Banana value:" + f.getValue() + "\n</description>\n<TimeStamp>\n<Start> " + fDate + "</Start>\n</TimeStamp>\n<Point>\n<coordinates>" + f.getPos().x() + "," + f.getPos().y() + "</coordinates>\n</Point>\n");
                        Kfile.append("<Style>\n<IconStyle>\n<scale>0.5</scale>\n<Icon>\n<href>banana.png</href>\n <refreshInterval>0.5</refreshInterval>\n<viewRefreshTime>0.5</viewRefreshTime>\n<viewBoundScale>0.5</viewBoundScale>\n</Icon>\n</IconStyle>\n</Style>\n</Placemark>\n");
                    }

                }
                try {
                    Thread.sleep(500);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                rg.scenario.game.move();
            }
            Kfile.append("</Document>\n</kml>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveFile();
    }

    /**
     * The actual method that writes the data into a file
     */
    private static boolean saveFile() {

        boolean flag = false;
        try {

            FileWriter writer = new FileWriter(scenarioInt + ".kml", true);
            writer.write(String.valueOf(Kfile));
            writer.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }
}


