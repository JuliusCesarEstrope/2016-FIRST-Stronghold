package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.PulseCommand;


public class DrawbridgeResetGyro extends PulseCommand {
	
	protected void pulse() {
		drawbridge.resetGyro();
	}
	
}
