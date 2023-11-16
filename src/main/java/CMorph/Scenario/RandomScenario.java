package CMorph.Scenario;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;
import CMorph.Config.Config;
import CMorph.Drone.Drone;
import CMorph.Drone.DroneInfo;
import CMorph.Server.Server;
import CMorph.Server.ServerInfo;

public class RandomScenario {
   
    private Random random;
    private Server[] Servers;
    private Drone [] Drones;
    private List<DroneInfo> droneData;
    private List<ServerInfo> serverData;
    private ObjectMapper objectMapper;

    public RandomScenario(){
        random = new Random();
        Servers = new Server[Config.NUM_OF_SERVERS];
        Drones = new Drone[Config.NUM_OF_DRONE];
        droneData = new ArrayList<>();
        serverData = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }

    public void runSimulation(){

        for (int i=0;i<Config.NUM_OF_SERVERS;i++){
            Servers[i]=new Server(Config.SeverXCoordinate[i], Config.SeverYCoordinate[i]);
        }

        for (int i=0;i<Config.NUM_OF_DRONE;i++){
            int RandomX = random.nextInt(500);
            int RandomY = random.nextInt(500);
            double s = ((double) Config.mapHeight-RandomY)/Config.SimStep;
            Drones[i] = new Drone(RandomX, RandomY, s);
            double bestCost = Double.MAX_VALUE;
            int bestServer =0;
            for (int j = 0;j<Config.NUM_OF_SERVERS;j++){
                double cost =Servers[j].Cost(Drones[i]);
                if (cost<bestCost){
                    bestCost= cost;
                    bestServer = j;
                }

            }
            Drones[i].setCurrentServer(bestServer);
            Servers[bestServer].addDrone();
        }

        for (int t=0;t<Config.SimStep;t++){
            for (int i=0;i<Config.NUM_OF_DRONE;i++){
                Drones[i].updateCoordinatey(t);
                double bestCost = Double.MAX_VALUE;
                int bestServer =0;
                for (int j = 0;j<Config.NUM_OF_SERVERS;j++){
                    double cost =Servers[j].Cost(Drones[i]);
                    if (cost<bestCost){
                         bestCost= cost;
                         bestServer = j;
                    }

                }
                Servers[bestServer].addDrone();
                Servers[Drones[i].getCurrentServer()].subDrone();
                Drones[i].setCurrentServer(bestServer);
                System.out.println(i + ":" +Drones[i].getCurrentY() + ", connected :" + Drones[i].getCurrentServer());
                // System.out.println(Drones[i].getCurrentX());
                DroneInfo droneInfo = new DroneInfo(i, Drones[i].getCurrentY(), Drones[i].getCurrentX(), Drones[i].getCurrentServer());
                droneData.add(droneInfo);
            }
            for (int k = 0;k<Config.NUM_OF_SERVERS;k++){
                ServerInfo serverInfo = new ServerInfo(k, Servers[k].getLoad());
                serverData.add(serverInfo);
            }


        }
        try (FileWriter fileWriter = new FileWriter("/home/masuda/Doxuments/CMorph/output/data.json", true)) {
            objectMapper.writeValue(fileWriter, droneData);
                } catch(IOException e){
                    e.printStackTrace();
                }
        
        try (FileWriter fileWriter = new FileWriter("/home/masuda/Doxuments/CMorph/output/serverdata.json", true)) {
            objectMapper.writeValue(fileWriter, serverData);
                } catch(IOException e){
                    e.printStackTrace();
                }
        
		

	}
    


}
