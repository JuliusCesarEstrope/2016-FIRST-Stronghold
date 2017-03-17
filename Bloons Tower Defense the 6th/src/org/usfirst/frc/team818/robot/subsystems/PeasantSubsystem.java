package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.RobotUtilities;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class PeasantSubsystem extends Subsystem {
	
	private static final double MAX_SPEED = 1;
	private static final double MIN_SPEED = 0.4;
	
	private Talon[] motors;
	private boolean peasantEnabled;
	
	public PeasantSubsystem(int[] motorPorts, boolean peasantEnabled) {
		
		this.peasantEnabled = peasantEnabled;
		
		if (peasantEnabled) {
			try {
				
				motors = new Talon[motorPorts.length];
				
				for (int i = 0; i < motorPorts.length; i++) {
					motors[i] = new Talon(motorPorts[i]);
				}
				
				RobotLog.putMessage("The peasant is prepared for strenuous labor");
				
			} catch (Exception e) {
				RobotLog.putMessage("Tried to initialize the peasant, but an error occured:");
				RobotLog.putMessage(e.getMessage());
			}
		}
		
	}
	
	public void initDefaultCommand() {
	}
	
	public void setSpeed(double speed) {
		if (peasantEnabled) {
			speed = RobotUtilities.limitSpeed(speed, MIN_SPEED, MAX_SPEED);
			for (int i = 0; i < motors.length; i++) {
				motors[i].set(speed);
			}
		}
		
	}
}
