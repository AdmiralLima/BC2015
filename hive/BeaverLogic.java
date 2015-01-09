package hive;

import battlecode.common.*;

public class BeaverLogic extends RobotLogic {
	
	private RobotController myController;
	
	private boolean miner;
	private final int ATTACKPORT = 0;
	
	public BeaverLogic(RobotController controller)
	{
		super();
		myController = controller;
		miner = false;

	}
	
	public void run()
	{
		try {
			//for beavers, what if we change this to a, if under attack, fight back, else go mine/build
			attack();
			/*MapLocation tower = myController.senseTowerLocations()[0];
			if (tower.distanceSquaredTo(myController.getLocation()) > 10)
			{
				Movement.moveToward(myController, tower);
			}
			else
			{
				if (!miner)
				{
					
				}
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void attack() throws Exception
	{
		if (myController.isWeaponReady())
		{
			RobotInfo[] enemies = myController.senseNearbyRobots(RobotType.BEAVER.attackRadiusSquared, myController.getTeam().opponent());
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
	
	public MapLocation getAttTarget(){
		int msg;
		try {
			msg = myController.readBroadcast(ATTACKPORT);
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			msg = 0;
		}
		int x = msg/1000;
		int y = (msg-x*1000);
		return new MapLocation(x,y);		
	}
	
	public void move(MapLocation target){
		
	}
}
