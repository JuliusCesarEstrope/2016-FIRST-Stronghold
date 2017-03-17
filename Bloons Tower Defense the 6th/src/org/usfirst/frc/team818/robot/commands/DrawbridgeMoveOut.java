package org.usfirst.frc.team818.robot.commands;

public class DrawbridgeMoveOut extends CommandBase {
	
	public DrawbridgeMoveOut() {
		requires(drawbridge);
	}
	
	protected void initialize() {
		drawbridge.setSpeed(1);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return drawbridge.getAngle() > 160 || drawbridge.getOuterLimit();
	}
	
	protected void end() {
		drawbridge.setSpeed(0);
	}
	
	protected void interrupted() {
		drawbridge.setSpeed(0);
	}
	
}
