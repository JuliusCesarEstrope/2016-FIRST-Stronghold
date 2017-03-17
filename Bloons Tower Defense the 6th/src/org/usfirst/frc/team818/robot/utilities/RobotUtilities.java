package org.usfirst.frc.team818.robot.utilities;

public abstract class RobotUtilities {
	
	private static final double DEFAULT_MAX_SPEED = 1;
	private static final double DEFAULT_MIN_SPEED = 0.1;
	
	public enum LEDColor {
		
		BLACK(false, false, false), RED(true, false, false), GREEN(false, true, false), BLUE(false, false, true), YELLOW(true, true, false), PINK(true, false, true), AQUA(false, true, true);
		
		public boolean red, green, blue;
		
		LEDColor(boolean red, boolean green, boolean blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
	}
	
	public static double limitSpeed(double proposedSpeed) {
		return limitSpeed(proposedSpeed, DEFAULT_MIN_SPEED, DEFAULT_MAX_SPEED);
	}
	
	public static double limitSpeed(double proposedSpeed, double maxSpeed) {
		return limitSpeed(proposedSpeed, DEFAULT_MIN_SPEED, maxSpeed);
	}
	
	public static double limitSpeed(double proposedSpeed, double minSpeed, double maxSpeed) {
		proposedSpeed *= maxSpeed;
		if (Math.abs(proposedSpeed) < minSpeed) {
			proposedSpeed = 0;
		}
		if (!(Math.abs(proposedSpeed) <= maxSpeed)) {
			return (proposedSpeed > 0) ? maxSpeed : -maxSpeed;
		} else {
			return proposedSpeed;
		}
	}
	
}
