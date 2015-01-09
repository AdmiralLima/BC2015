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
			RobotActions.attack(myController, RobotType.TOWER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
