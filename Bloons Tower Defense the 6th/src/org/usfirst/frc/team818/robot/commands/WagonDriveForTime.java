package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;


public class WagonDriveForTime extends CommandBase {
	
	private Timer timer;
	
	private double time;
	private double speedLeft, speedRight;
	
	public WagonDriveForTime(double speedLeft, double speedRight, double time) {
		
		requires(wagon);
		
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
		this.time = time;
		
		timer = new Timer();
		
	}
	
	public WagonDriveForTime(double speed, double time) {
		this(speed, speed, time);
	}
	
	protected void initialize() {
		wagon.set(speedLeft, speedRight);
		timer.start();
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}
	
	protected void end() {
		timer.stop();
		wagon.set(0, 0);
	}
	
	protected void interrupted() {
		timer.stop();
		wagon.set(0, 0);
	}
	
}
