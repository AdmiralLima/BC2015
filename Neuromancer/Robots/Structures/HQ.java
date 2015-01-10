package Neuromancer.Robots.Structures;

import Neuromancer.Robots.Robot;
import battlecode.common.*;
import Neuromancer.actions.*;

public class HQ extends Robot {
	
	private final RobotController mc;

	public HQ(RobotController myController)
	{
		super(myController);
		mc = myController;
	}
	
	public void run() {
		try {
			Actions.attack(mc);
			Actions.spawn(mc, RobotType.BEAVER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
