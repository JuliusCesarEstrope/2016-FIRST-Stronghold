package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class WagonRotate extends CommandBase {
	
	private double angle, speed;
	private Timer timer;
	
	public WagonRotate(double angle) {
		requires(wagon);
		this.angle = angle;
		timer = new Timer();
	}
	
	protected void initialize() {
		
		speed = 0;
		
		angle = SmartDashboard.getNumber("Number 1");
		wagon.setRotatePID(SmartDashboard.getNumber("Number 2"), SmartDashboard.getNumber("Number 3"), SmartDashboard.getNumber("Number 4"));
		
		wagon.resetGyro();
		wagon.setRotatePoint(angle);
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
