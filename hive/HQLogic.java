package hive;

import battlecode.common.*;

public class HQLogic extends RobotLogic {
	
	private RobotController myController;

	public HQLogic(RobotController controller)
	{
		super();
		myController = controller;
	}
	
	public void run()
	{
		try {
			RobotActions.attack(myController, RobotType.HQ);
			RobotActions.spawn(myController, RobotType.BEAVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
