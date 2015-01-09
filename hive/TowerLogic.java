package hive;

import battlecode.common.*;

public class TowerLogic extends RobotLogic {
	
	private RobotController myController;
	
	public TowerLogic(RobotController controller)
	{
		super();
		myController = controller;
	}
	
	public void run()
	{
		try {
			attack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void attack() throws Exception
	{
		if (myController.isWeaponReady())
		{
			RobotInfo[] enemies = myController.senseNearbyRobots(RobotType.TOWER.attackRadiusSquared, myController.getTeam().opponent());
			RobotInfo bestEnemy = null;
			int bestIterator = 22;
			for (RobotInfo enemy : enemies)
			{
				if (bestEnemy == null)
				{
					bestEnemy = enemy;
				}
				else
				{
					int i = 0;
					while(i < attackPreference.length)
					{
						if (enemy.type == attackPreference[i])
						{
							break;
						}
						i++;
					}
					if (i < bestIterator)
					{
						bestEnemy = enemy;
						bestIterator = i;
					}
					else if (i == bestIterator)
					{
						double bestHealth = bestEnemy.health / attackPreference[i].maxHealth;
						double enemyHealth = enemy.health / attackPreference[i].maxHealth;
						if (enemyHealth < bestHealth)
						{
							bestEnemy = enemy;
							bestIterator = i;
						}
					}
				}
			}
			if (bestEnemy != null)
			{
				if(myController.canAttackLocation(bestEnemy.location))
				{
					myController.attackLocation(bestEnemy.location);
				}
			}
		}
	}
}
