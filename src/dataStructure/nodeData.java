package dataStructure;

import utils.Point3D;

import java.io.Serializable;
import java.util.Comparator;

public class nodeData implements node_data, Serializable,Comparator<nodeData> {
    Point3D location=null;
    Integer id=0;
    Double weight=0.0;
    String inf="";
    int color=0;

    public nodeData(){
       this.location = null;
       this.id = null;
       this.weight = null;
       this.inf=null;
       this.color=0;
    }
    public nodeData(Point3D a,int d,Double w,String in,int co){
        this.location=a;
        this.id=d;
        this.weight=w;
        this.inf=in;
        this.color=co;
    }
    public nodeData(Point3D a,int d,Double w,int co){
        this.location=a;
        this.id=d;
        this.weight=w;
    //    this.inf=in;
    //    this.color=co;
    }
    public nodeData(nodeData a) {
        nodeData b = new nodeData(a.location,a.id,a.weight,a.inf,a.color);

    }

    public nodeData(node_data nd) {
        this.location=nd.getLocation();
        this.id=nd.getKey();
        this.weight=nd.getWeight();
        this.inf=nd.getInfo();
        this.color=nd.getTag();
    }

    public nodeData(int x, int y) {
        Point3D p = new Point3D(x,y,0);
        this.location=p;
    }


    @Override
    public int getKey() {

        return this.id;
    }

    @Override
    public Point3D getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Point3D p) {
    this.location=p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
    if(w<0){weight = null;}
    else{this.weight=w;}
    }

    @Override
    public String getInfo() {
        return this.inf;
    }

    @Override
    public void setInfo(String s) {
    this.inf=s;
    }

    @Override
    public int getTag() {
        return this.color;
    }

    @Override
    public void setTag(int t) {
    if(t<3 && t>-1){this.color=t;}
    else{this.color=0;}
    }

    @Override
    public int compare(nodeData ed, nodeData ed2) {
        if (ed.getWeight() < ed2.getWeight()) return -1;
        if (ed.getWeight() > ed2.getWeight()) return 1;
        return 0;
    }
    public  boolean equals(Object nd1){
        if(!(nd1 instanceof nodeData))return false;
        nodeData nd = (nodeData) nd1;
        if(this.location.equalsXY(nd.location)  && this.color == nd.color && this.inf.equals(nd.inf) && this.weight == nd.weight)return true;
        return false;
    }


}
