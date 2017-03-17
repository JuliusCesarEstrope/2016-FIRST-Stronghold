package org.usfirst.frc.team818.robot.commands;


public class WagonStraight extends CommandBase {
	
	private double speedLeft, speedRight;
	
	public WagonStraight() {
		requires(wagon);
	}
	
	protected void initialize() {
		
		speedLeft = 0;
		speedRight = 0;
		
		wagon.resetGyro();
		wagon.enablePID("straight");
		
	}
	
	protected void execute() {
		
		speedLeft = 0.8 * oi.leftY();
		speedRight = 0.8 * oi.leftY();
		
		speedRight -= wagon.getPIDOutput();
		
		wagon.set(speedLeft, speedRight);
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		wagon.set(0, 0);
		wagon.disablePID();
	}
	
	protected void interrupted() {
		wagon.set(0, 0);
		wagon.disablePID();
	}
	
}
