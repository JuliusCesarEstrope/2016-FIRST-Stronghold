package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.WagonStraightForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class GenericDefenseAuton extends CommandGroup {
	
	public GenericDefenseAuton() {
		addSequential(new WagonStraightForTime(0.6, 3.5));
	}
}
