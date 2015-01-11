package BumbleBitch;

import battlecode.common.*;

public class MinerFactory extends Robot {
	public MinerFactory(RobotController myController)
	{
		super(myController);
	}
	
	public void run()
	{
		try {
			spawn();
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean spawn() throws GameActionException {
		if (controller.isCoreReady()) {
			Direction start = controller.senseEnemyHQLocation().directionTo(controller.getLocation());
			for (int i = 0; i < 8; i++) {
				start = start.rotateRight();
				if (controller.canSpawn(start, RobotType.MINER)) {
					controller.spawn(start, RobotType.MINER);
					return true;
				}
			}
		}
		return false;
	}
}
