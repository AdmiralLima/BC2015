package BumbleBitch;

import battlecode.common.*;

public class Drone extends Robot {
	
	public Drone(RobotController myController)
	{
		super(myController);
	}
	
	public void run()
	{
		try {
			if(!attack())
			{
				if (controller.readBroadcast(0) != 1)
				{
					if (controller.getSupplyLevel() < 50)
					{
						moveToward(controller.senseHQLocation());
					}
					else
					{
						moveToward(controller.senseTowerLocations()[0]);
					}
				}
				else
				{
					MapLocation[] towers = controller.senseEnemyTowerLocations();		
					MapLocation bestTower = null;
					int bestDistance = 5000;
					for (MapLocation tower : towers)
					{
						int distance = controller.getLocation().distanceSquaredTo(tower);
						if (distance < bestDistance)
						{
							bestTower = tower;
						}
					}
					if (bestTower != null)
					{
						moveToward(bestTower);
					}
					else
					{
						moveToward(controller.senseEnemyHQLocation());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean attack() throws Exception
	{
		if (controller.isWeaponReady())
		{
			RobotInfo[] enemies = controller.senseNearbyRobots(RobotType.DRONE.attackRadiusSquared, controller.getTeam().opponent());
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
				if (enemy.type == RobotType.TOWER)
				{
					bestEnemy = enemy;
					break;
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
	
	public boolean moveToward(MapLocation location) throws Exception
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
}
