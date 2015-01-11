package BumbleBitch;

import battlecode.common.*;

public class Miner extends Robot {

public Miner(RobotController myController)
{
	super(myController);
}
public void run()
{
	try {
		if(controller.isCoreReady())
		{
			controller.mine();
		}
	} catch (GameActionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
