package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;


public class WagonRotateToGyro extends CommandBase {
	
	private double speed;
	private Timer timer;
	
	public WagonRotateToGyro() {
		requires(wagon);
		timer = new Timer();
	}
	
	protected void initialize() {
		
		speed = 0;
		
		wagon.setRotatePoint(0);
		wagon.enablePID("rotate");
		
		timer.start();
		
	}
	
	protected void execute() {
		
		speed = wagon.getPIDOutput();
		wagon.set(speed, -speed);
		
	}
	
	protected boolean isFinished() {
		return wagon.rotateOnTarget() || timer.hasPeriodPassed(3);
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
