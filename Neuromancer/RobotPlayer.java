package Neuromancer;

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
				break;
			case MINER:
				break;
			case MINERFACTORY:
				break;
			case MISSILE:
				break;
			case SOLDIER:
				break;
			case SUPPLYDEPOT:
				break;
			case TANK:
				break;
			case TANKFACTORY:
				break;
			case TECHNOLOGYINSTITUTE:
				break;
			case TOWER:
				break;
			case TRAININGFIELD:
				break;
			default:
				myLogic = new Robot(controller);
				break;
		}
		
		/*
		 * Run unit logic for the duration of the game.
		 */
		while(true)
		{
			myLogic.run();
			controller.yield();
		}
	}
}
