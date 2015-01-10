package Neuromancer.actions;

import battlecode.common.*;
import Neuromancer.*;

import java.util.Random;

public class Actions {
	
	/*
	 * Tries to spawn the given unit type.
	 */
	public static boolean spawn(RobotController mc, RobotType type) throws GameActionException {
		if (mc.isCoreReady()) {
			for (Direction dir : Utils.directions) {
				if (mc.canSpawn(dir, type)) {
					mc.spawn(dir, type);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Attacks nearby enemies indiscriminately.
	 */
	public static boolean attack(RobotController mc) throws GameActionException {
		if (mc.isWeaponReady()) {
			RobotInfo[] enemies = mc.senseNearbyRobots(mc.getType().attackRadiusSquared, mc.getTeam().opponent());
			if (enemies.length > 0) {
				RobotInfo enemy = enemies[0];
				if(mc.canAttackLocation(enemy.location)) {
					mc.attackLocation(enemy.location);
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Moves robot in random direction as long as it is at least square-distance of 4 away from HQ.
	 */
	public static void moveRandom(RobotController mc) throws GameActionException {
		if (mc.isCoreReady()) {
			while (true) {
				Random random = new Random();
				int dirnum = random.nextInt(8);
				Direction dir = Utils.directions[dirnum];
				if (mc.canMove(dir)) {
					mc.move(dir);
					break;
				}
			}
		}
	}
	
	public static boolean attack(RobotController controller, RobotType type) throws GameActionException
	{
		if (controller.isWeaponReady())
		{
			RobotInfo[] enemies = controller.senseNearbyRobots(type.attackRadiusSquared, controller.getTeam().opponent());
			RobotInfo bestEnemy = null;
			double bestHealth = 1.1;
			for (RobotInfo enemy : enemies)
			{
				double enemyHealth = enemy.health / enemy.type.maxHealth;
				if (enemyHealth < bestHealth)
				{
					bestEnemy = enemy;
					bestHealth = enemyHealth;
				}
			}
			if (bestEnemy != null)
			{
				if(controller.canAttackLocation(bestEnemy.location))
				{
					controller.attackLocation(bestEnemy.location);
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * Moves robots away from HQ.
	 */
	public static boolean dissipateFromHQ(RobotController mc) throws GameActionException {
		if (mc.isCoreReady()) {
			MapLocation hqLocation = mc.senseHQLocation();
			MapLocation myLocation = mc.getLocation();
			if (myLocation.distanceSquaredTo(hqLocation) < 4 && mc.canMove(myLocation.directionTo(hqLocation).opposite())) {
				mc.move(myLocation.directionTo(hqLocation).opposite());
				return false;
			}
			return true;
		}
		return false;
	}
	
	/*
	 * Tries to mine ore.
	 */
	public static boolean mine(RobotController mc) throws GameActionException {
		if (dissipateFromHQ(mc) && mc.canMine() && mc.isCoreReady()) {
			mc.mine();
			return true;
		}
		return false;
	}

}
