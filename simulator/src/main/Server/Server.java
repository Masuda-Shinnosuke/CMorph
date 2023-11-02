package simulator.src.main.Server;
import simulator.src.main.Drone.Drone;
import simulator.src.main.Config.Config;
import java.util.ArrayList;

public class Server {
    double x_coordinate;
    double y_coordinate;
    double rho;
    int ConnectedDrone = 0;


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

    public double getLoad(){

        if (this.ConnectedDrone==0){
            rho = 0.0;
        }else if(this.ConnectedDrone==1){
            rho = 0.25;
        }else if(this.ConnectedDrone==2){
            rho =0.5;
        }else if(this.ConnectedDrone==3){
            rho =0.75;
        }else{
            rho =1.0;
        }
        return rho;
    }


    public double Cost(Drone drone){
        double dist= Math.sqrt(Math.pow((this.x_coordinate-drone.getCurrentX()),2)+Math.pow((this.y_coordinate-drone.getCurrentY()),2));
        double cost =Math.pow((2*this.getLoad()-1), 2)/(1-this.getLoad())+dist/Math.sqrt(Config.mapHeight*Config.mapWidth+Config.mapHeight*Config.mapWidth);

        return cost;
    }

    
}
