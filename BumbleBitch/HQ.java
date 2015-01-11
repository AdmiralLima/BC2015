package BumbleBitch;

import Neuromancer.Utils;
import battlecode.common.*;

public class HQ extends Robot
{
	public HQ(RobotController myController)
	{
		super(myController);
	}
	
	public void run()
	{
		try 
		{
			if(!attack())
			{
				RobotInfo[] friends = controller.senseNearbyRobots(RobotType.HQ.sensorRadiusSquared, controller.getTeam());
				int beavercount = 0;
				for (RobotInfo friend : friends)
				{
					if (friend.type == RobotType.BEAVER)
					{
						beavercount++;
					}
				}
				if (beavercount < 6)
				{
					spawn();
				}
			}
			supply();
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
			RobotInfo[] enemies = controller.senseNearbyRobots(RobotType.HQ.attackRadiusSquared, controller.getTeam().opponent());
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
	
	public boolean spawn() throws GameActionException {
		if (controller.isCoreReady()) {
			Direction start = controller.senseEnemyHQLocation().directionTo(controller.getLocation());
			for (int i = 0; i < 8; i++) {
				start = start.rotateRight();
				if (controller.canSpawn(start, RobotType.BEAVER)) {
					controller.spawn(start, RobotType.BEAVER);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean supply() throws Exception
	{
		RobotInfo[] friends = controller.senseNearbyRobots(15, controller.getTeam());
		for (RobotInfo friend : friends)
		{
			if (friend.type == RobotType.DRONE)
			{
				if (controller.getSupplyLevel() > 5000)
				{
					if (friend.supplyLevel < 50)
					{
						controller.transferSupplies(5000, friend.location);
						return true;
					}
				}
			}
		}
		return false;
	}
}
