package BumbleBitch;

import battlecode.common.*;

public class Miner extends Robot {

	public Miner(RobotController myController)
	{
		super(myController);
	}
	public void run()
	{
		try 
		{
			moreOre();
			if(controller.isCoreReady())
			{
				controller.mine();
			}
		} 
		catch (GameActionException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean moreOre() throws GameActionException
	{
		if (controller.isCoreReady())
		{
			MapLocation currentLoc = controller.getLocation();
			double current = controller.senseOre(controller.getLocation());
			if (current > 1)
			{
				return false;
			}
			Direction direction =  currentLoc.directionTo(controller.senseHQLocation());
			for (int i = 0; i < 8; i++)
			{
				for (int j = i; j > 0; j--)
				{
					if (i % 2 == 1)
					{
						direction = direction.rotateRight();
					}
					else 
					{
						direction = direction.rotateLeft();
					}
				}
				if (controller.senseOre(currentLoc.add(direction)) > 5)
				{
					if(controller.canMove(direction))
					{
						controller.move(direction);
						return true;
					}
				}
			}
		}
		return false;
	}
}
