package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.LanternController;
import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.RobotUtilities.LEDColor;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class LanternSubsystem extends Subsystem {
	
	private DigitalOutput redDO, greenDO, blueDO;
	private boolean[] state = new boolean[3];
	private boolean ledsEnabled;
	
	public LanternSubsystem(int[] colorPorts, boolean ledsEnabled) {
		
		this.ledsEnabled = ledsEnabled;
		
		if (ledsEnabled) {
			try {
				redDO = new DigitalOutput(colorPorts[0]);
				greenDO = new DigitalOutput(colorPorts[1]);
				blueDO = new DigitalOutput(colorPorts[2]);
				RobotLog.putMessage("The lantern is lit to light the way");
			} catch (Exception e) {
				this.ledsEnabled = false;
				RobotLog.putMessage("Tried initializing the LEDs, but an error occured:");
				RobotLog.putMessage(e.getMessage());
			}
		}
		
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new LanternController());
	}
	
	public void setColor(LEDColor color) {
		setColors(color.red, color.green, color.blue);
	}
	
	public void setColors(boolean redState, boolean greenState, boolean blueState) {
		setRed(redState);
		setGreen(greenState);
		setBlue(blueState);
	}
	
	public void setRed(boolean redState) {
		if (ledsEnabled) {
			state[0] = redState;
			redDO.set(redState);
		}
	}
	
	public void setGreen(boolean greenState) {
		if (ledsEnabled) {
			state[1] = greenState;
			greenDO.set(greenState);
		}
	}
	
	public void setBlue(boolean blueState) {
		if (ledsEnabled) {
			state[2] = blueState;
			blueDO.set(blueState);
		}
	}
	
	public boolean[] getState() {
		return state;
	}
	
	public boolean getRedState() {
		return state[0];
	}
	
	public boolean getGreenState() {
		return state[1];
	}
	
	public boolean getBlueState() {
		return state[2];
	}
}
