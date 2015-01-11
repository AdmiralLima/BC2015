package BumbleBitch;

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
				myLogic = new Robot(controller);
				break;
			case BARRACKS:
				myLogic = new Robot(controller);
				break;
			case BASHER:
				myLogic = new Robot(controller);
				break;
			case BEAVER:
				myLogic = new Beaver(controller);
				break;
			case COMMANDER:
				myLogic = new Robot(controller);
				break;
			case COMPUTER:
				myLogic = new Robot(controller);
				break;
			case DRONE:
				myLogic = new Drone(controller);
				break;
			case HANDWASHSTATION:
				myLogic = new Robot(controller);
				break;
			case HELIPAD:
				myLogic = new Helipad(controller);
				break;
			case HQ:
				myLogic = new HQ(controller);
				break;
			case LAUNCHER:
				myLogic = new Robot(controller);
				break;
			case MINER:
				myLogic = new Miner(controller);
				break;
			case MINERFACTORY:
				myLogic = new MinerFactory(controller);
				break;
			case MISSILE:
				myLogic = new Robot(controller);
				break;
			case SOLDIER:
				myLogic = new Robot(controller);
				break;
			case SUPPLYDEPOT:
				myLogic = new Robot(controller);
				break;
			case TANK:
				myLogic = new Robot(controller);
				break;
			case TANKFACTORY:
				myLogic = new Robot(controller);
				break;
			case TECHNOLOGYINSTITUTE:
				myLogic = new Robot(controller);
				break;
			case TOWER:
				myLogic = new Tower(controller);
				break;
			case TRAININGFIELD:
				myLogic = new Robot(controller);
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