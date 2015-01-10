package Neuromancer.actions;

import battlecode.common.*;

public class Actions {
	
	/*
	 * Used for easy access to directions.
	 */
	public static Direction[] directions = {Direction.NORTH, 
									 Direction.NORTH_EAST, 
									 Direction.EAST, 
									 Direction.SOUTH_EAST, 
									 Direction.SOUTH, 
									 Direction.SOUTH_WEST, 
									 Direction.WEST, 
									 Direction.NORTH_WEST};
	
	/*
	 * Tries to spawn the given unit type.
	 */
	public static void spawn(RobotController mc, RobotType type) throws GameActionException {
		if (mc.isCoreReady()) {
			for (Direction dir : directions) {
				if (mc.canSpawn(dir, type)) {
					mc.spawn(dir, type);
				}
			}
		}
	}
	
	/*
	 * Attacks nearby enemies indiscriminately.
	 */
	public static void attack(RobotController mc) throws GameActionException {
		if (mc.isWeaponReady()) {
			RobotInfo[] enemies = mc.senseNearbyRobots(mc.getType().attackRadiusSquared, mc.getTeam().opponent());
			if (enemies.length > 0) {
				RobotInfo enemy = enemies[0];
				if(mc.canAttackLocation(enemy.location)) {
					mc.attackLocation(enemy.location);
				}
			}
		}
	}

}
