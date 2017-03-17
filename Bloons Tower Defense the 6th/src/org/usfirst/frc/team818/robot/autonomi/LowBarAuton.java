package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveOut;
import org.usfirst.frc.team818.robot.commands.WagonStraightForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class LowBarAuton extends CommandGroup {
	
	public LowBarAuton() {
		addSequential(new WagonStraightForTime(-0.4, 0.5));
		addSequential(new DrawbridgeMoveOut(), 4.5);
		addSequential(new WagonStraightForTime(-0.6, 2.5));
	}
}
