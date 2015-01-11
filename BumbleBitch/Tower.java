package BumbleBitch;

import battlecode.common.*;

public class Tower extends Robot
{
	public Tower(RobotController myController)
	{
		super(myController);
	}
	
	public void run()
	{
		try 
		{
			attack();
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
			RobotInfo[] enemies = controller.senseNearbyRobots(RobotType.TOWER.attackRadiusSquared, controller.getTeam().opponent());
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
}
