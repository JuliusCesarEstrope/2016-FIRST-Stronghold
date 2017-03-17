package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.PulseCommand;


public class BallistaPrime extends PulseCommand {
	
	protected void pulse() {
		ballista.setSpeed(1);
	}
	
}
