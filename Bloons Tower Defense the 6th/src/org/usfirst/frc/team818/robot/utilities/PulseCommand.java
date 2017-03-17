package org.usfirst.frc.team818.robot.utilities;

import org.usfirst.frc.team818.robot.commands.CommandBase;


public abstract class PulseCommand extends CommandBase {
	
	protected abstract void pulse();
	
	protected void initialize() {
		pulse();
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
	}
	
	protected void interrupted() {
	}
	
}
