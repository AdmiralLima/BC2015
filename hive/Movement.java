package hive;

import battlecode.common.*;

public class Movement 
{
	
	/*
	 * Stupidly moves toward the given location.
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
	 * Stupidly moves away from the given location.
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
}
