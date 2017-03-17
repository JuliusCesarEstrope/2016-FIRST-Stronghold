package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;


public class DrawbridgeMoveToAngle extends CommandBase {
	
	private double angle;
	private boolean shouldCease;
	
	private Timer timer;
	
	public DrawbridgeMoveToAngle(double angle) {
		requires(drawbridge);
		this.angle = angle;
		timer = new Timer();
	}
	
	protected void initialize() {
		drawbridge.setSetpoint(angle);
		drawbridge.enable();
		timer.start();
		shouldCease = false;
	}
	
	protected void execute() {
		
		double currentSpeed = drawbridge.getSpeed();
		
		if (currentSpeed > 0 && drawbridge.getOuterLimit()) {
			shouldCease = true;
		} else if (currentSpeed < 0 && drawbridge.getInnerLimit()) {
			shouldCease = true;
		}
		
	}
	
	protected boolean isFinished() {
		return shouldCease || drawbridge.onTarget() || timer.hasPeriodPassed(3);
	}
	
	protected void end() {
		drawbridge.disable();
		timer.stop();
		drawbridge.setSpeed(0);
	}
	
	protected void interrupted() {
		drawbridge.disable();
		timer.stop();
		drawbridge.setSpeed(0);
	}
	
}
