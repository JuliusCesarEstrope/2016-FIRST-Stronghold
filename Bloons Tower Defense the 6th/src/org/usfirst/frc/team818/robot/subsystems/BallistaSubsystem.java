package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.RobotUtilities;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class BallistaSubsystem extends Subsystem {
	
	private static final double MAX_SPEED = 1;
	
	private boolean ballistaEnabled;
	
	private Talon motor;
	// private RPMSensor rpmSensor;
	
	public BallistaSubsystem(int motorPort, int rpmSensorPort, boolean ballistaEnabled) {
		
		this.ballistaEnabled = ballistaEnabled;
		
		if (ballistaEnabled) {
			try {
				
				motor = new Talon(motorPort);
				
				RobotLog.putMessage("The ballista is primed and ready to impale");
				
			} catch (Exception e) {
				RobotLog.putMessage("Tried initializing the ballista, but an error occured:");
				RobotLog.putMessage(e.getMessage());
			}
		}
		
	}
	
	protected void initDefaultCommand() {
		// setDefaultCommand(new Thingie());
	}
	
	public void setSpeed(double speed) {
		if (ballistaEnabled) {
			speed = RobotUtilities.limitSpeed(speed, MAX_SPEED);
			motor.set(speed);
		}
	}
	
}
