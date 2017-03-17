package org.usfirst.frc.team818.robot.commands;


public class DrawbridgeMoveVariable extends CommandBase {
	
	private double speed;
	private double axisValue;
	
	public DrawbridgeMoveVariable() {
		requires(drawbridge);
	}
	
	protected void initialize() {
	}
	
	protected void execute() {
		
		axisValue = oi.drawbridgeAxis();
		
		if (axisValue > 0.5) {
			speed = (axisValue - 0.45) / 0.55;
			if (drawbridge.getOuterLimit())
				speed = 0;
		} else if (axisValue < -0.5) {
			speed = (axisValue + 0.45) / 0.55;
			if (drawbridge.getInnerLimit())
				speed = 0;
		} else {
			speed = 0;
		}
		
		drawbridge.setSpeed(speed);
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		drawbridge.setSpeed(0);
	}
	
	protected void interrupted() {
		drawbridge.setSpeed(0);
	}
	
}
