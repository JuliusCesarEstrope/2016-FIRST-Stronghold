package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotUtilities.LEDColor;


public class LanternController extends CommandBase {
	
	public LanternController() {
		requires(lantern);
	}
	
	protected void initialize() {
		lantern.setColor(LEDColor.BLACK);
	}
	
	protected void execute() {
		lantern.setRed(oi.getRed());
		lantern.setGreen(oi.getGreen());
		lantern.setBlue(oi.getBlue());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		lantern.setColor(LEDColor.BLACK);
	}
	
	protected void interrupted() {
		lantern.setColor(LEDColor.BLACK);
	}
	
}
