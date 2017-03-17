package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.PulseCommand;


public class BallistaDisable extends PulseCommand {
	
	protected void pulse() {
		ballista.setSpeed(0);
	}
	
}
