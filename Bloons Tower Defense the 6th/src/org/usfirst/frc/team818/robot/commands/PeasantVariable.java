package org.usfirst.frc.team818.robot.commands;

public class PeasantVariable extends CommandBase {
	
	private double speed;
	private double axisValue;
	
	public PeasantVariable() {
		requires(peasant);
	}
	
	protected void initialize() {
	}
	
	protected void execute() {
		
		axisValue = oi.peasantAxis();
		
		if (axisValue > 0) {
			speed = (axisValue - 0.45) / 0.55;
		} else if (axisValue < 0) {
			speed = (axisValue + 0.45) / 0.55;
		} else {
			speed = 0;
		}
		
		peasant.setSpeed(speed);
		
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
