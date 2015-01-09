package hive;

import battlecode.common.*;

public class RobotActions {
	
	/*
	 * Used for navigation and spawning.
	 */
	static Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};

	/*
	 * Spawns the given unit in any available direction.
	 */
	public static boolean spawn(RobotController controller, RobotType type) throws Exception
	{
		if(controller.isCoreReady())
		{
			for(Direction direction : directions)
			{
				if(controller.canSpawn(direction, type))
				{
					controller.spawn(direction, type);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Builds the given unit in any available direction.
	 */
	public static boolean build(RobotController controller, RobotType type) throws Exception
	{
		if(controller.isCoreReady())
		{
			for(Direction direction : directions)
			{
				if(controller.canBuild(direction, type))
				{
					controller.build(direction, type);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Moves toward the given location.
	 */
	public static boolean moveToward(RobotController controller, MapLocation location) throws Exception
	{
		if(controller.isCoreReady())
		{
			Direction current = controller.getLocation().directionTo(location);
			for (int i = 0; i < 8; i++)
			{
				if(i % 2 == 0)
				{
					for (int j = i; j > 0; j--)
					{
						current = current.rotateLeft();
					}
				}
				else
				{
					for (int j = i; j > 0; j--)
					{
						current = current.rotateRight();
					}
				}
				if (controller.canMove(current))
				{
					controller.move(current);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Moves away from the given location.
	 */
	public static boolean moveAwayFrom(RobotController controller, MapLocation location) throws Exception
	{
		if(controller.isCoreReady())
		{
			Direction current = controller.getLocation().directionTo(location).opposite();
			for (int i = 0; i < 8; i++)
			{
				if(i % 2 == 0)
				{
					for (int j = i; j > 0; j--)
					{
						current = current.rotateLeft();
					}
				}
				else
				{
					for (int j = i; j > 0; j--)
					{
						current = current.rotateRight();
					}
				}
				if (controller.canMove(current))
				{
					controller.move(current);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Robot will try to attack enemies in order of importance.
	 */
	public static boolean attack(RobotController controller, RobotType type) throws Exception
	{
		if (controller.isWeaponReady())
		{
			RobotInfo[] enemies = controller.senseNearbyRobots(type.attackRadiusSquared, controller.getTeam().opponent());
			RobotInfo bestEnemy = null;
			for(RobotInfo enemy : enemies)
			{
				if(bestEnemy == null)
				{
					bestEnemy = enemy;
				}
				else if(enemy.type == RobotType.HQ)
				{
					bestEnemy = enemy;
				}
				else if(enemy.type == RobotType.TOWER && bestEnemy.type != RobotType.HQ)
				{
					bestEnemy = enemy;
				}
				else if(enemy.type == RobotType.MISSILE && bestEnemy.type != RobotType.HQ && bestEnemy.type != RobotType.TOWER)
				{
					bestEnemy = enemy;
				}
				else if(enemy.type == RobotType.TANK && bestEnemy.type != RobotType.HQ && bestEnemy.type != RobotType.TOWER && bestEnemy.type != RobotType.MISSILE)
				{
					bestEnemy = enemy;
				}
				else if(enemy.type == RobotType.COMMANDER && bestEnemy.type != RobotType.HQ && bestEnemy.type != RobotType.TOWER && bestEnemy.type != RobotType.MISSILE && bestEnemy.type != RobotType.TANK)
				{
					bestEnemy = enemy;
				}
			}
			if(bestEnemy != null)
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
}
