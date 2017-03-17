package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;


public class Wait extends CommandBase {
	
	private Timer timer;
	private double time;
	
	public Wait(double time) {
		timer = new Timer();
		this.time = time;
	}
	
	protected void initialize() {
		timer.start();
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}
	
	protected void end() {
	}
	
	protected void interrupted() {
	}
	
}
