package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveOut;
import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveToAngle;
import org.usfirst.frc.team818.robot.commands.WagonStraightForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class PortcullisAuton extends CommandGroup {
	
	public PortcullisAuton() {
		addSequential(new DrawbridgeMoveOut());
		addSequential(new WagonStraightForTime(0.3, 2.4));
		addParallel(new DrawbridgeMoveToAngle(45));
		addSequential(new WagonStraightForTime(0.25, 2.5));
		addParallel(new DrawbridgeMoveToAngle(25));
		addSequential(new WagonStraightForTime(0.4, 3));
	}
}
