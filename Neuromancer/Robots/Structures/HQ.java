package Neuromancer.Robots.Structures;

import Neuromancer.Pathfinding.PathFinder;
import Neuromancer.Robots.Robot;
import battlecode.common.*;
import Neuromancer.actions.*;

public class HQ extends Robot {
	
	private final RobotController mc;
	
	private PathFinder paths;
	
	private int round;

	public HQ(RobotController myController)
	{
		super(myController);
		mc = myController;
		paths = new PathFinder(controller);
		round = 0;
	}
	
	public void run() {
		int start = Clock.getBytecodeNum();
		
		for (int i = (1800 - Clock.getBytecodeNum() + start) / 150; i > 0; i--)
		{
			paths.scan();
		}
	}
}
