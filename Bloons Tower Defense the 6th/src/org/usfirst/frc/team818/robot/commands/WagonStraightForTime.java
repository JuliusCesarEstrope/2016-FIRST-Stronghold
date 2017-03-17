package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;


public class WagonStraightForTime extends CommandBase {
	
	private Timer timer;
	
	private double speed, time;
	private double speedLeft, speedRight;
	
	public WagonStraightForTime(double speed, double time) {
		
		requires(wagon);
		
		this.speed = speed;
		this.time = time;
		
		timer = new Timer();
		
	}
	
	protected void initialize() {
		
		speedLeft = 0;
		speedRight = 0;
		
		wagon.resetGyro();
		wagon.enablePID("straight");
		
		timer.start();
		
	}
	
	protected void execute() {
		
		speedLeft = 0.8 * speed;
		speedRight = 0.8 * speed - wagon.getPIDOutput();
		
		wagon.set(speedLeft, speedRight);
		
	}
	
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}
	
	protected void end() {
		timer.stop();
		wagon.disablePID();
		wagon.set(0, 0);
	}
	
	protected void interrupted() {
		timer.stop();
		wagon.disablePID();
		wagon.set(0, 0);
	}
	
}
