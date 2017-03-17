package org.usfirst.frc.team818.robot.commands;

public class WagonArcade extends CommandBase {
	
	private double speedLeft, speedRight, speedX, speedY;
	
	public WagonArcade() {
		requires(wagon);
	}
	
	protected void initialize() {
		speedLeft = 0;
		speedRight = 0;
	}
	
	protected void execute() {
		
		speedX = oi.rightX();
		speedY = oi.leftY();
		
		if (speedY > 0) {
			if (speedX > 0) {
				speedLeft = speedY - speedX;
				speedRight = Math.max(speedX, speedY);
			} else {
				speedLeft = Math.max(-speedX, speedY);
				speedRight = speedY + speedX;
			}
		} else {
			if (speedX > 0) {
				speedLeft = -Math.max(speedX, -speedY);
				speedRight = speedY + speedX;
			} else {
				speedLeft = speedY - speedX;
				speedRight = -Math.max(-speedX, -speedY);
			}
		}
		
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
