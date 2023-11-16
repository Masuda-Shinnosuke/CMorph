package CMorph.Simulator;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import CMorph.Drone.Drone;
import CMorph.Server.Server;
import CMorph.Server.ServerInfo;
import CMorph.Config.Config;
import CMorph.Drone.DroneInfo;
import CMorph.Scenario.RandomScenario;
import CMorph.Scenario.StayScenario;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	public static void main(String[] args) throws ParseException {

        StayScenario simulation = new StayScenario();
        simulation.runSimulation();

        

	}
}