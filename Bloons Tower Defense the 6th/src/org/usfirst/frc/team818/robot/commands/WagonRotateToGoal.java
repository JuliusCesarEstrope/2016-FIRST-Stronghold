package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.VisionUtilities;

import com.ni.vision.NIVision;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.Timer;


public class WagonRotateToGoal extends CommandBase {
	
	private double angle, speed;
	
	private NIVision.Image image;
	private Timer timer;
	
	public WagonRotateToGoal() {
		requires(wagon);
		timer = new Timer();
	}
	
	protected void initialize() {
		
		if (peephole.getFacing().equals("Main")) peephole.swapView();
		
		if (peephole.getFacing().equals("Shoot")) {
			
			speed = 0;
			try {
				image = peephole.fetchImage();
				angle = VisionUtilities.getGoalAngle(image);
			} catch (VisionException ve) {
				RobotLog.putMessage("Error fetching image!");
				angle = 0;
			}
			
			wagon.resetGyro();
			wagon.setRotatePoint(angle);
			wagon.enablePID("rotate");
			
			timer.start();
			
		}
		
	}
	
	protected void execute() {
		
		if (peephole.getFacing().equals("Shoot")) {
			
			speed = wagon.getPIDOutput();
			wagon.set(speed, -speed);
			
		}
		
	}
	
	protected boolean isFinished() {
		return (peephole.getFacing().equals("Shoot")) ? (wagon.rotateOnTarget() || timer.hasPeriodPassed(3)) : true;
	}
	
	protected void end() {
		if (peephole.getFacing().equals("Shoot")) {
			timer.stop();
			wagon.disablePID();
			wagon.set(0, 0);
		}
	}
	
	protected void interrupted() {
		if (peephole.getFacing().equals("Shoot")) {
			timer.stop();
			wagon.disablePID();
			wagon.set(0, 0);
		}
	}
	
}
