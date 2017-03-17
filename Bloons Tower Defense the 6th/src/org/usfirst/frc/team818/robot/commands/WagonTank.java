package org.usfirst.frc.team818.robot.commands;


public class WagonTank extends CommandBase {
	
	private double speedLeft, speedRight;
	
	public WagonTank() {
		requires(wagon);
	}
	
	protected void initialize() {
		speedLeft = 0;
		speedRight = 0;
	}
	
	protected void execute() {
		
		speedLeft = oi.leftY();
		speedRight = oi.rightY();
		
		wagon.set(speedLeft, speedRight);
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		wagon.set(0, 0);
	}
	
	protected void interrupted() {
		wagon.set(0, 0);
	}
	
}
