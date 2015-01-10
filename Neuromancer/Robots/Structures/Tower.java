package Neuromancer.Robots.Structures;

import Neuromancer.Robots.Robot;
import Neuromancer.actions.Actions;
import battlecode.common.*;

public class Tower extends Robot {
	public Tower(RobotController myController)
	{
		super(myController);
	}
	
	public void run()
	{
		try {
			Actions.attack(controller, RobotType.TOWER);
		} catch (GameActionException e) {
			e.printStackTrace();
		}
	}
}
