package org.usfirst.frc.team818.robot.commands;

public class DrawbridgeMoveIn extends CommandBase {
	
	public DrawbridgeMoveIn() {
		requires(drawbridge);
	}
	
	protected void initialize() {
		drawbridge.setSpeed(-1);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return drawbridge.getInnerLimit();
	}
	
	protected void end() {
		drawbridge.setSpeed(0);
	}
	
	protected void interrupted() {
		drawbridge.setSpeed(0);
	}
	
}
