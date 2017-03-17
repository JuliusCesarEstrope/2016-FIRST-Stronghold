package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class FullFire extends CommandGroup {
	
	public FullFire() {
		
		addSequential(new PeasantEject(), 3);
		addSequential(new BallistaDisable());
		
	}
}
