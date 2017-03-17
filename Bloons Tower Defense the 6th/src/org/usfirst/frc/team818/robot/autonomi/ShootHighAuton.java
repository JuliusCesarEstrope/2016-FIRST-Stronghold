package org.usfirst.frc.team818.robot.autonomi;

import org.usfirst.frc.team818.robot.commands.BallistaDisable;
import org.usfirst.frc.team818.robot.commands.BallistaPrime;
import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveIn;
import org.usfirst.frc.team818.robot.commands.PeasantEject;
import org.usfirst.frc.team818.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class ShootHighAuton extends CommandGroup {
	
	public ShootHighAuton() {
		
		addParallel(new DrawbridgeMoveIn());
		addSequential(new BallistaPrime());
		addSequential(new Wait(0.5));
		addSequential(new PeasantEject(), 2);
		addSequential(new BallistaDisable());
		
	}
}
