package simulator.src.main.Drone;

public class Drone {
    double x_coordinate;
    double y_coordinate;
    double initialY;
    public double InitialSpeed;
    public double Speed =0.0;
    public int ConnectedSeverId;

    public Drone(double x,double y,double s){
        this.x_coordinate=x;
        this.initialY=y;
        this.y_coordinate=y;
        this.InitialSpeed=s;
    }

    public void AccelerateDrone(int a,int t){
        this.Speed=InitialSpeed+a*t/1000;
    }

    public void updateCoordinatey(int t){
        this.y_coordinate=initialY+InitialSpeed*t;
    }

    public double getCurrentX(){
        return this.x_coordinate;
    }
    public double getCurrentY(){
        return this.y_coordinate;
    }

    public void connectServer(int id){
        this.ConnectedSeverId = id;
    }

    public int getCurrentServer(){
        return this.ConnectedSeverId;
    }

}
