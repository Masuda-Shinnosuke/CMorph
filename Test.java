import java.text.ParseException;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import simulator.src.main.Drone.Drone;
import simulator.src.main.Server.Server;
import simulator.src.main.Config.Config;

public class Test {
	public static void main(String[] args) throws ParseException {

        Random random = new Random();
        Server[] Servers = new Server[Config.NUM_OF_SERVERS];
        Drone[] Drones = new Drone[Config.NUM_OF_DRONE];

        for (int i=0;i<Config.NUM_OF_SERVERS;i++){
            Servers[i]=new Server(Config.SeverXCoordinate[i], Config.SeverYCoordinate[i]);
        }

        for (int i=0;i<Config.NUM_OF_DRONE;i++){
            int RandomX = random.nextInt(100);
            int RandomY = random.nextInt(100);
            double s = (Config.mapHeight-RandomY)/Config.SimStep;
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
            Drones[i].connectServer(bestServer);
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
                Drones[i].connectServer(bestServer);
                System.out.println(i + ":" +Drones[i].getCurrentY() + ", connected :" + Drones[i].getCurrentServer());

            }

        }
		
	}
}