package Neuromancer;

import Neuromancer.Robots.Robot;
import Neuromancer.Robots.Structures.AerospaceLab;
import Neuromancer.Robots.Structures.Barracks;
import Neuromancer.Robots.Structures.HQ;
import Neuromancer.Robots.Structures.HandwashingStation;
import Neuromancer.Robots.Structures.Helipad;
import Neuromancer.Robots.Structures.MinerFactory;
import Neuromancer.Robots.Structures.SupplyDepot;
import Neuromancer.Robots.Structures.TankFactory;
import Neuromancer.Robots.Structures.TechnologyInstitute;
import Neuromancer.Robots.Structures.Tower;
import Neuromancer.Robots.Structures.TrainingField;
import Neuromancer.Robots.Units.Basher;
import Neuromancer.Robots.Units.Beaver;
import Neuromancer.Robots.Units.Commander;
import Neuromancer.Robots.Units.Computer;
import Neuromancer.Robots.Units.Drone;
import Neuromancer.Robots.Units.Launcher;
import Neuromancer.Robots.Units.Miner;
import Neuromancer.Robots.Units.Missile;
import Neuromancer.Robots.Units.Soldier;
import Neuromancer.Robots.Units.Tank;
import battlecode.common.*;

/*
 * An instance is created for every unit.
 */
public class RobotPlayer 
{
	
	/*
	 * Run at the start of each unit's existence.
	 */
	public static void run(RobotController controller)
	{
		
		/*
		 * Determine unit type.
		 */
		RobotType myType = controller.getType();
		
		/*
		 * The robot logic is unit type specific.
		 */
		Robot myLogic;
		
		/*
		 * Initialize logic based on unit type.
		 */
		switch(myType)
		{
			case AEROSPACELAB:
				myLogic = new AerospaceLab(controller);
				break;
			case BARRACKS:
				myLogic = new Barracks(controller);
				break;
			case BASHER:
				myLogic = new Basher(controller);
				break;
			case BEAVER:
				myLogic = new Beaver(controller);
				break;
			case COMMANDER:
				myLogic = new Commander(controller);
				break;
			case COMPUTER:
				myLogic = new Computer(controller);
				break;
			case DRONE:
				myLogic = new Drone(controller);
				break;
			case HANDWASHSTATION:
				myLogic = new HandwashingStation(controller);
				break;
			case HELIPAD:
				myLogic = new Helipad(controller);
				break;
			case HQ:
				myLogic = new HQ(controller);
				break;
			case LAUNCHER:
				myLogic = new Launcher(controller);
				break;
			case MINER:
				myLogic = new Miner(controller);
				break;
			case MINERFACTORY:
				myLogic = new MinerFactory(controller);
				break;
			case MISSILE:
				myLogic = new Missile(controller);
				break;
			case SOLDIER:
				myLogic = new Soldier(controller);
				break;
			case SUPPLYDEPOT:
				myLogic = new SupplyDepot(controller);
				break;
			case TANK:
				myLogic = new Tank(controller);
				break;
			case TANKFACTORY:
				myLogic = new TankFactory(controller);
				break;
			case TECHNOLOGYINSTITUTE:
				myLogic = new TechnologyInstitute(controller);
				break;
			case TOWER:
				myLogic = new Tower(controller);
				break;
			case TRAININGFIELD:
				myLogic = new TrainingField(controller);
				break;
			default:
				myLogic = new Robot(controller);
				break;
		}
		
		/*
		 * Run unit logic for the duration of its life.
		 */
		while(true)
		{
			myLogic.run();
			controller.yield();
		}
	}
}
