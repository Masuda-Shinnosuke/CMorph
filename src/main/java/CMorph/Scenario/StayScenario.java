package CMorph.Scenario;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.SERVICE_FORMATTED;

import CMorph.Config.Config;
import CMorph.Drone.Drone;
import CMorph.Drone.DroneInfo;
import CMorph.Server.Server;
import CMorph.Server.ServerInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StayScenario {
    private Server [] Servers;
    private ArrayList<Drone> Drones = new ArrayList<>();
    private List<DroneInfo> droneData;
    private List<ServerInfo> serverData;
    private ObjectMapper objectMapper;

    public StayScenario(){
        Servers = new Server[Config.NUM_OF_SERVERS];
        // initialDrones = new Drone[400];
        // Drones = new Drone[400];
        droneData=new ArrayList<>();
        serverData=new ArrayList<>();
        objectMapper = new ObjectMapper();
    }

   
    
    public void runSimulation(){
        
        for (int i = 0;i<Config.NUM_OF_SERVERS;i++){
            Servers[i] = new Server(Config.StayServerx[i], Config.StayServery[i]);
        }

        

        for (int t = 0;t<Config.SimStep;t++){

            Drone drone = new Drone(150, 150, 1);
            Drones.add(drone);

            
            if (t%10==0&&t>0){
                Drones.set((t/10)-1,null);
            }

            for (int j = 0;j<Drones.size();j++){

                if (Drones.get(j) !=null){
                    double bestCost=Double.MAX_VALUE;
                    int bestServer = 0;

                    for (int k = 0;k<Config.NUM_OF_SERVERS;k++){
                        double cost = Servers[k].Cost(Drones.get(j));
                        if (cost<bestCost) {
                            bestCost=cost;
                            bestServer=k;
                        }
                    }
                    // 接続しているサーバがあったら接続を解除してセットしなおす
                    // 接続サーバーがない時は接続する
                    if (Drones.get(j).getCurrentServer()==-1){
                        Drones.get(j).setCurrentServer(bestServer);
                        Servers[bestServer].addDrone();
                    }else{
                        // 接続サーバが前回と同じなら変えない
                        if (Drones.get(j).getCurrentServer()!=bestServer){
                            Servers[Drones.get(j).getCurrentServer()].subDrone();
                            Drones.get(j).setCurrentServer(bestServer);
                            Servers[bestServer].addDrone();
                        }
                    }
                    DroneInfo droneInfo =new DroneInfo(j, Drones.get(j).getCurrentY(), Drones.get(j).getCurrentX(), Drones.get(j).getCurrentServer());
                    droneData.add(droneInfo);
                }
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
