package Neuromancer.Robots;

import battlecode.common.*;

/*
 * This class is meant to be overridden by specific unit types.
 */
public class Robot {

	/*
	 * The controller for this unit.
	 */
	protected RobotController controller;
	
	/*
	 * Constructor simply takes the unit's controller.
	 */
	public Robot(RobotController myController)
	{
		controller = myController;
	}
	
	/*
	 * Every unit should have a run method that overwrites this method.
	 */
	public void run(){}
}
