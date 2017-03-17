package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.PeasantEject;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class ShootLowAuton extends CommandGroup {
	
	public ShootLowAuton() {
		
		addSequential(new PeasantEject(), 2);
		
	}
}
