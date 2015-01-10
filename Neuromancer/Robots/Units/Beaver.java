package Neuromancer.Robots.Units;

import Neuromancer.Robots.Robot;
import Neuromancer.actions.Actions;
import battlecode.common.*;
import java.util.Random;

public class Beaver extends Robot {
	
	private final RobotController mc;
	
	public Beaver(RobotController myController)
	{
		super(myController);
		mc = myController;
	}
	
	public void run() {
		try {
			boolean attacked = Actions.attack(mc);
			mc.setIndicatorString(0, "attacked = ".concat(String.valueOf(attacked)));
			Random rand = new Random();
			int actnum = rand.nextInt(2);
			if (actnum == 0) {
				boolean mined = Actions.mine(mc);
				mc.setIndicatorString(1, "mined = ".concat(String.valueOf(mined)));
			} else {
				Actions.moveRandom(mc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
