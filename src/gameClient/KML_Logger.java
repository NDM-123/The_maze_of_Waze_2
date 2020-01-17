package gameClient;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataStructure.graph;
import dataStructure.node_data;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

public class KML_Logger {

  public static boolean KML_Save(graph gra, List<String> robots,List<String> fruits){
        try {
            Kml kl = new Kml();
            Document document = kl.createAndSetDocument().withName("Map.scenario");
            for (int i = 0; i < robots.size(); i++) {
                TimeStamp t = new TimeStamp();
                t.setWhen(new Date().toString());
                Placemark placemark = document.createAndAddPlacemark();
                String j = robots.get(i);
                placemark.withTimePrimitive(t).createAndSetPoint().addToCoordinates(j);
            }
            for (int i = 0; i < fruits.size(); i++) {
                TimeStamp t = new TimeStamp();
                t.setWhen(new Date().toString());
                Placemark placemark = document.createAndAddPlacemark();
                String j = fruits.get(i);
                placemark.withTimePrimitive(t).createAndSetPoint().addToCoordinates(j);
            }
            Collection<node_data> ver = gra.getV();
            Iterator it = ver.iterator();
            while(it.hasNext()){
                TimeStamp t = new TimeStamp();
                t.setWhen(new Date().toString());
                Placemark placemark = document.createAndAddPlacemark();
//                String j = get(it.next().);
//                placemark.withTimePrimitive(t).createAndSetPoint().addToCoordinates(j);

            }
//            try {
//                System.out.println("hi");
//         //       kl.marshal(new File(file));
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//                //     Logger.getLogger(KMLFile.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }catch(Exception e){
            e.printStackTrace();
        }



        /**
         * @param args
         */
//        public static void main(String[] args) {
//            // TODO Auto-generated method stub
//            String file = "1.kml";
//            test1(file);
//        }
//
//        public static void test1(String file) {
//            try {
//                final Kml kml = new Kml();
//                Document document = kml.createAndSetDocument().withName("Map.scenario");
//                for(int i=0;i<10;i++) {
//                    TimeStamp t = new TimeStamp();
//                    t.setWhen(new Date().toString());
//                    Placemark placemark = document.createAndAddPlacemark();
//                    placemark.withTimePrimitive(t).createAndSetPoint().addToCoordinates(35,32+i/1000.0,i);
//                }
//                try {
//                    kml.marshal(new File(file));
//                } catch (FileNotFoundException ex) {
//                    ex.printStackTrace();
//                    //     Logger.getLogger(KMLFile.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
      return false;
        }

    }

