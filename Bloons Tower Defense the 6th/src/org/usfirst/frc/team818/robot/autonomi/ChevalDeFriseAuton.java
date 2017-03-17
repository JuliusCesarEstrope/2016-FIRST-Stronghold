package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveOut;
import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveToAngle;
import org.usfirst.frc.team818.robot.commands.WagonStraightForTime;
import org.usfirst.frc.team818.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class ChevalDeFriseAuton extends CommandGroup {
	
	public ChevalDeFriseAuton() {
		addSequential(new WagonStraightForTime(0.3, 2));
		addSequential(new DrawbridgeMoveOut());
		addParallel(new WagonStraightForTime(0.5, 3));
		addSequential(new Wait(0.5));
		addSequential(new DrawbridgeMoveToAngle(90));
		//addSequential(new WagonStraightForTime(0.5, 1));
	}
}
