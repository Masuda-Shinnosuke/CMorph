package CMorph.Server;
import CMorph.Drone.Drone;
import CMorph.Config.Config;
import java.util.ArrayList;

public class Server {
    public double x_coordinate;
    public double y_coordinate;
    public double rho;
    public int ConnectedDrone = 0;


    public Server(double x, double y){
        this.x_coordinate=x;
        this.y_coordinate=y;
    }

    public void addDrone(){
        this.ConnectedDrone++;
    }

    public void subDrone(){
        this.ConnectedDrone--;
    }

    public int getCurrentDrone(){
        
        return this.ConnectedDrone;
    }

    public double getLoad(){

        rho=ConnectedDrone*0.01;

        return rho;
    }


    public double Cost(Drone drone){
        double dist= Math.sqrt(Math.pow((this.x_coordinate-drone.getCurrentX()),2)+Math.pow((this.y_coordinate-drone.getCurrentY()),2));
        double cost =Math.pow((2*this.getLoad()-1), 2)/(1-this.getLoad())+dist/Math.sqrt(Config.mapHeight*Config.mapWidth+Config.mapHeight*Config.mapWidth);

        return cost;
    }

    
}
