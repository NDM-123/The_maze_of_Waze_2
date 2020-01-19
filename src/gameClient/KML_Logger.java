package gameClient;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;
import de.micromata.opengis.kml.v_2_2_0.*;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import utils.Point3D;


public class KML_Logger {
    static StringBuilder Kfile = new StringBuilder();
   private static int scenarioInt;


    public static boolean KML_Save(Scenario s) {
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
        Kfile.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
                "  <Document>\n" +
                "    <name>The maze of Waze 2</name>");
        Iterator it = robots.iterator();
        while (it.hasNext()) {
            Robot r = new Robot((String) it.next());
            Kfile.append("<Placemark> \n<TimeStamp>\n<when> " + fDate + "\n</when>\n<Point>\n<coordinates> (" + r.getPos().x() + "," + r.getPos().y() + ")\n</coordinates>\n</Point>\n");
            Kfile.append("<Style>\n<IconStyle>\n<scale>1.0</scale>\n<Icon>\n<href>rob.png</href>\n</Icon>\n</IconStyle>\n</Style>\n");
        }
        Iterator it2 = fruits.iterator();
        while (it2.hasNext()) {
            Fruit f = new Fruit((String) it2.next());
            if (f.getType() == 1) {             //apple
                Kfile.append("<Placemark> \n<TimeStamp>\n<when> " + fDate + "\n</when>\n<Point>\n<coordinates> (" + f.getPos().x() + "," + f.getPos().y() + ")\n</coordinates>\n</Point>\n");
                Kfile.append("<Style>\n<IconStyle>\n<scale>1.0</scale>\n<Icon>\n<href>apple2.png</href>\n</Icon>\n</IconStyle>\n</Style>\n");
            } else {                           //banana
                Kfile.append("<Placemark> \n<TimeStamp>\n<when> " + fDate + "\n</when>\n<Point>\n<coordinates> (" + f.getPos().x() + "," + f.getPos().y() + ")\n</coordinates>\n</Point>\n");
                Kfile.append("<Style>\n<IconStyle>\n<scale>1.0</scale>\n<Icon>\n<href>banana.png</href>\n</Icon>\n</IconStyle>\n</Style>\n");
            }

        }
        Kfile.append("</Document>\n</kml>");
       return  saveFile();
    }

       private static boolean saveFile(){

        boolean flag = false;
        try {

            FileWriter writer = new FileWriter(scenarioInt+".kml",true);
            writer.write(String.valueOf(Kfile));
            writer.close();
            flag=true;
        }catch(Exception e){
          e.printStackTrace();
          flag = false;
        }

           return flag;
    }
}


