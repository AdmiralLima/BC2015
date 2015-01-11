package BumbleBitch;

import battlecode.common.*;

public class Helipad extends Robot {
	public Helipad(RobotController myController)
	{
		super(myController);
	}
	
	public void run()
	{
		try {
			
				RobotInfo[] friends = controller.senseNearbyRobots(10000, controller.getTeam());
				int drones = 0;
				int miners = 0;
				for (RobotInfo friend : friends)
				{
					if (friend.type == RobotType.DRONE)
					{
						drones++;
					}
					else if (friend.type == RobotType.MINER)
					{
						miners++;
					}
				}
				if (miners > 0)
				{
					if (drones / miners < 8)
					{
						spawn();
					}
				}
			
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
				if (controller.canSpawn(start, RobotType.DRONE)) {
					controller.spawn(start, RobotType.DRONE);
					return true;
				}
			}
		}
		return false;
	}
}
