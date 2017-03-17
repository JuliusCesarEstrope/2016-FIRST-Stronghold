package org.usfirst.frc.team818.robot.triggers;

import org.usfirst.frc.team818.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.buttons.Trigger;


public class DrawbridgeResetGyroTrigger extends Trigger {
	
	public boolean get() {
		return CommandBase.drawbridge.getResetLimit();
	}
	
}
