package BumbleBitch;

import java.util.Random;

import battlecode.common.*;

public class Beaver extends Robot
{
	Random rand;
	
	public Beaver(RobotController myController)
	{
		super(myController);
		rand = new Random(controller.getID());

	}
	
	public void run()
	{
		try 
		{
			if(!attack())
			{
				int what = rand.nextInt(20);
				if (what < 1)
				{
					if (!build(RobotType.HELIPAD))
					{
						mine();
					}
				}
				else if (what < 6 && controller.getLocation().distanceSquaredTo(controller.senseHQLocation()) < 25)
				{
					if(!moveAwayFrom(controller.senseHQLocation()))
					{
						mine();
					}
				}
				else
				{
					if (!build(RobotType.MINERFACTORY))
					{
						mine();
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean attack() throws Exception
	{
		if (controller.isWeaponReady())
		{
			RobotInfo[] enemies = controller.senseNearbyRobots(RobotType.BEAVER.attackRadiusSquared, controller.getTeam().opponent());
			RobotInfo bestEnemy = null;
			double bestHealth = 1.0;
			for (RobotInfo enemy : enemies)
			{
				double enemyHealth = enemy.health / enemy.type.maxHealth;
				if (enemyHealth <= bestHealth)
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
	
	public boolean build(RobotType type) throws GameActionException {
		if (controller.isCoreReady()) {
			Direction start = controller.senseEnemyHQLocation().directionTo(controller.getLocation());
			for (int i = 0; i < 8; i++) {
				start = start.rotateRight();
				if (controller.canBuild(start, type)) {
					controller.build(start, type);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean mine() throws Exception
	{
		if (controller.isCoreReady()) {
			controller.mine();
			return true;
		}
		return false;
	}
	
	public boolean moveAwayFrom(MapLocation location) throws Exception
	{
		if(controller.isCoreReady())
		{
			Direction current = controller.getLocation().directionTo(location).opposite();
			for (int i = 0; i < 4; i++)
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
}
