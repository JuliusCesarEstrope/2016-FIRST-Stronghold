package org.usfirst.frc.team818.robot;

import org.usfirst.frc.team818.robot.commands.*;
import org.usfirst.frc.team818.robot.triggers.DrawbridgeResetGyroTrigger;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


public class OI {
	
	private Joystick joystickLeft, joystickRight, controller;
	
	private JoystickButton driveStraight;
	private JoystickButton gathererGather, gathererEject;
	private JoystickButton drawbridgeVertical;
	private JoystickButton tempRotate;
	
	private Trigger swapDriveTrigger, swapPeepholeTrigger;
	private Trigger drawbridgeMoveVariableTrigger, drawbridgeResetGyroTrigger;
	private Trigger peasantVariableTrigger;
	private Trigger ballistaPrimeTrigger;
	
	// private JoystickButton peepholeGray;
	
	public OI() {
		
		RobotLog.putMessage("Binding stuff to things");
		
		joystickLeft = new Joystick(Constants.leftJoystickPort);
		joystickRight = new Joystick(Constants.rightJoystickPort);
		controller = new Joystick(Constants.controllerPort);
		
		driveStraight = new JoystickButton(joystickLeft, 6);
		gathererGather = new JoystickButton(controller, 5);
		gathererEject = new JoystickButton(controller, 7);
		drawbridgeVertical = new JoystickButton(controller, 3);
		tempRotate = new JoystickButton(controller, 10);
		
		swapDriveTrigger = new Trigger() {
			public boolean get() {
				return (joystickRight.getRawButton(8) && joystickRight.getRawButton(9) && Constants.tankSwapEnabled);
			}
		};
		swapPeepholeTrigger = new Trigger() {
			public boolean get() {
				return (joystickRight.getRawButton(2) || joystickLeft.getRawButton(2) || controller.getRawButton(1));
			}
		};
		drawbridgeMoveVariableTrigger = new Trigger() {
			public boolean get() {
				return (Math.abs(drawbridgeAxis()) > 0.5);
			}
		};
		peasantVariableTrigger = new Trigger() {
			public boolean get() {
				return (Math.abs(peasantAxis()) > 0.5);
			}
		};
		ballistaPrimeTrigger = new Trigger() {
			public boolean get() {
				return (controller.getRawButton(6) || controller.getRawButton(8));
			}
		};
		drawbridgeResetGyroTrigger = new DrawbridgeResetGyroTrigger();
		
		driveStraight.whileHeld(new WagonStraight());
		gathererGather.whileHeld(new PeasantGather());
		gathererEject.whileHeld(new PeasantEject());
		
		swapDriveTrigger.toggleWhenActive(new WagonArcade());
		swapPeepholeTrigger.whenActive(new PeepholeSwap());
		drawbridgeMoveVariableTrigger.whileActive(new DrawbridgeMoveVariable());
		peasantVariableTrigger.whileActive(new PeasantVariable());
		ballistaPrimeTrigger.whenActive(new BallistaPrime());
		ballistaPrimeTrigger.whenInactive(new BallistaDisable());
		drawbridgeResetGyroTrigger.whenActive(new DrawbridgeResetGyro());
		drawbridgeVertical.whenPressed(new DrawbridgeMoveToAngle(90));
		//tempRotate.whenPressed(new WagonRotate(90));
		
	}
	
	public double leftX() {
		return -joystickLeft.getX();
	}
	
	public double leftY() {
		return -joystickLeft.getY();
	}
	
	public double rightX() {
		return -joystickRight.getX();
	}
	
	public double rightY() {
		return -joystickRight.getY();
	}
	
	public double drawbridgeAxis() {
		return controller.getRawAxis(0);
	}
	
	public double ballistaAimAxis() {
		return -controller.getRawAxis(3);
	}
	
	public double peasantAxis() {
		return controller.getRawAxis(2);
	}
	
	public boolean driveSlow() {
		return joystickRight.getRawButton(11);
	}
	
	public boolean getRed() {
		return joystickRight.getRawButton(4);
	}
	
	public boolean getGreen() {
		return joystickRight.getRawButton(3);
	}
	
	public boolean getBlue() {
		return joystickRight.getRawButton(5);
	}
	
}
