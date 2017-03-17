package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.DrawbridgeMoveVariable;
import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.RobotUtilities;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class DrawbridgeSubsystem extends PIDSubsystem {
	
	private static final double[] PID_VALUES = { 0.05, 0, 0.1 };
	private static final double[] PID_RANGE = { -1, 1 };
	private static final double PID_TOLERANCE = 3;
	private static final double MAX_SPEED = 1;
	private static final double[] ANGLE_RANGE = { 0, 220 };
	
	private Talon liftMotor;
	private DigitalInput resetLimit, outerLimit;
	private AnalogGyro gyro;
	
	private boolean drawbridgeEnabled;
	
	public DrawbridgeSubsystem(int motorPort, int[] limitSwitchPorts, int gyroPort, boolean drawbridgeEnabled) {
		
		super(PID_VALUES[0], PID_VALUES[1], PID_VALUES[2]);
		this.drawbridgeEnabled = drawbridgeEnabled;
		
		if (drawbridgeEnabled) {
			try {
				
				liftMotor = new Talon(motorPort);
				resetLimit = new DigitalInput(limitSwitchPorts[0]);
				outerLimit = new DigitalInput(limitSwitchPorts[1]);
				gyro = new AnalogGyro(gyroPort);
				
				setOutputRange(PID_RANGE[0], PID_RANGE[1]);
				setAbsoluteTolerance(PID_TOLERANCE);
				getPIDController().setContinuous(false);
				
				RobotLog.putMessage("The drawbridge is ready to refuse entry to adversaries");
				
			} catch (Exception e) {
				RobotLog.putMessage("Tried to initialize the drawbridge, but an error occured:");
				RobotLog.putMessage(e.getMessage());
			}
		}
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DrawbridgeMoveVariable());
	}
	
	public void setSpeed(double speed) {
		if (drawbridgeEnabled) {
			liftMotor.set(RobotUtilities.limitSpeed(speed, MAX_SPEED));
		}
	}
	
	public double getSpeed() {
		return (drawbridgeEnabled) ? liftMotor.get() : 0;
	}
	
	public boolean getResetLimit() {
		return (drawbridgeEnabled) ? resetLimit.get() : false;
	}
	
	public boolean getInnerLimit() {
		return (drawbridgeEnabled) ? resetLimit.get() : false;
	}
	
	public boolean getOuterLimit() {
		return (drawbridgeEnabled) ? ((getAngle() >= ANGLE_RANGE[1]) || /*outerLimit.get()*/ false) : false;
	}
	
	public void resetGyro() {
		if (drawbridgeEnabled)
			gyro.reset();
	}
	
	public double getAngle() {
		return (drawbridgeEnabled) ? -gyro.getAngle() : 0;
	}
	
	@Override
	protected double returnPIDInput() {
		return getAngle();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		setSpeed(output);
	}
	
}
