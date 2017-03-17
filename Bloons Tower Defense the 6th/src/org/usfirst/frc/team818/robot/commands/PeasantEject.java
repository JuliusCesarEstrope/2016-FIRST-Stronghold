package org.usfirst.frc.team818.robot.commands;

public class PeasantEject extends CommandBase {
	
	public PeasantEject() {
		requires(peasant);
	}
	
	protected void initialize() {
		peasant.setSpeed(1);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		peasant.setSpeed(0);
	}
	
	protected void interrupted() {
		peasant.setSpeed(0);
	}
	
}
