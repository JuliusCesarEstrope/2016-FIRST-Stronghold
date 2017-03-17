package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.CommandBase;
import org.usfirst.frc.team818.robot.commands.WagonTank;
import org.usfirst.frc.team818.robot.utilities.DoublePIDOutput;
import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.RobotUtilities;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class WagonSubsystem extends Subsystem {
	
	private static final double LEFT_F_CORRECTION = 1;
	private static final double LEFT_B_CORRECTION = 1;
	private static final double RIGHT_F_CORRECTION = 1;
	private static final double RIGHT_B_CORRECTION = 1;
	
	private static final double MAX_SPEED = 0.9;
	private static final double SLOW_SPEED = 0.45;
	
	private static final double[] STRAIGHT_PID_VALUES = { 0.1, 0.001, 0 };
	private static final double[] STRAIGHT_PID_RANGE = { -0.2, 0.2 };
	private static final double[] ROTATE_PID_VALUES = { 0.05, 0, 0.1 };
	private static final double[] ROTATE_PID_RANGE = { -0.4, 0.4 };
	private static final double ROTATE_PID_TOLERANCE = 1;
	
	private boolean wagonEnabled;
	
	private Talon[] motorsLeft;
	private Talon[] motorsRight;
	private AnalogGyro gyro;
	private PIDController straightController, rotateController;
	private DoublePIDOutput pidOutput;
	
	public WagonSubsystem(int[] leftPorts, int[] rightPorts, int gyroPort, boolean wagonEnabled) {
		
		this.wagonEnabled = wagonEnabled;
		
		if (wagonEnabled) {
			try {
				
				motorsLeft = new Talon[leftPorts.length];
				motorsRight = new Talon[rightPorts.length];
				
				for (int i = 0; i < leftPorts.length; i++) {
					motorsLeft[i] = new Talon(leftPorts[i]);
				}
				for (int i = 0; i < rightPorts.length; i++) {
					motorsRight[i] = new Talon(rightPorts[i]);
				}
				
				gyro = new AnalogGyro(gyroPort);
				
				pidOutput = new DoublePIDOutput();
				
				straightController = new PIDController(STRAIGHT_PID_VALUES[0], STRAIGHT_PID_VALUES[1], STRAIGHT_PID_VALUES[2], gyro, pidOutput);
				straightController.setOutputRange(STRAIGHT_PID_RANGE[0], STRAIGHT_PID_RANGE[1]);
				straightController.setSetpoint(0);
				straightController.setContinuous(false);
				
				rotateController = new PIDController(ROTATE_PID_VALUES[0], ROTATE_PID_VALUES[1], ROTATE_PID_VALUES[2], gyro, pidOutput);
				rotateController.setOutputRange(ROTATE_PID_RANGE[0], ROTATE_PID_RANGE[1]);
				rotateController.setAbsoluteTolerance(ROTATE_PID_TOLERANCE);
				rotateController.setContinuous();
				
				RobotLog.putMessage("The wagon is rigged up to bring out the dead");
				
			} catch (Exception e) {
				this.wagonEnabled = false;
				RobotLog.putMessage("Tried initializing the wagon, but an error occured:");
				RobotLog.putMessage(e.getMessage());
			}
		}
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new WagonTank());
	}
	
	public void setLeft(double speed) {
		if (wagonEnabled) {
			speed = RobotUtilities.limitSpeed(speed, (CommandBase.oi.driveSlow()) ? SLOW_SPEED : MAX_SPEED);
			for (int i = 0; i < motorsLeft.length; i++) {
				motorsLeft[i].set(speed * ((speed < 0) ? LEFT_B_CORRECTION : LEFT_F_CORRECTION));
			}
		}
	}
	
	public void setRight(double speed) {
		if (wagonEnabled) {
			speed = RobotUtilities.limitSpeed(speed, (CommandBase.oi.driveSlow()) ? SLOW_SPEED : MAX_SPEED);
			for (int i = 0; i < motorsRight.length; i++) {
				motorsRight[i].set(-speed * ((speed < 0) ? RIGHT_B_CORRECTION : RIGHT_F_CORRECTION));
			}
		}
	}
	
	public void set(double speedLeft, double speedRight) {
		setLeft(speedLeft);
		setRight(speedRight);
	}
	
	public double getAngle() {
		return (wagonEnabled) ? gyro.getAngle() : 0;
	}
	
	public void resetGyro() {
		if (wagonEnabled)
			gyro.reset();
	}
	
	public void enablePID(String pidType) {
		if (wagonEnabled) {
			if (pidType.equals("straight")) {
				if (rotateController.isEnabled())
					rotateController.disable();
				if (!straightController.isEnabled())
					straightController.enable();
			} else if (pidType.equals("rotate")) {
				if (straightController.isEnabled())
					straightController.disable();
				if (!rotateController.isEnabled())
					rotateController.enable();
			}
		}
	}
	
	public void disablePID() {
		if (wagonEnabled) {
			if (rotateController.isEnabled())
				rotateController.disable();
			if (straightController.isEnabled())
				straightController.disable();
		}
	}
	
	public void setRotatePoint(double angle) {
		if (wagonEnabled)
			rotateController.setSetpoint(angle);
	}
	
	public boolean rotateOnTarget() {
		return (wagonEnabled) ? rotateController.onTarget() : true;
	}
	
	public double getPIDOutput() {
		return (wagonEnabled) ? pidOutput.get() : 0;
	}
	
	public void setRotatePID(double p, double i, double d) {
		rotateController.setPID(p, i, d);
	}
	
}
