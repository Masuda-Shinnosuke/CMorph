package CMorph.Drone;

public class DroneInfo {
    public int id;
    public double currentY;
    public double currentX;
    public int connectedSeverId;
   

    public DroneInfo(int id,double currentY,double currentX,int connectedSeverId){

        this.id=id;
        this.currentY=currentY;
        this.currentX=currentX;
        this.connectedSeverId=connectedSeverId;
        

    }
}
