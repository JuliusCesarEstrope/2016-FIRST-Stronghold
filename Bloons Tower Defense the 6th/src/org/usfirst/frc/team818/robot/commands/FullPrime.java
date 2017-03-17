package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class FullPrime extends CommandGroup {
	
	public FullPrime() {
		
		addParallel(new DrawbridgeMoveIn());
		addSequential(new BallistaPrime());
		
	}
}
