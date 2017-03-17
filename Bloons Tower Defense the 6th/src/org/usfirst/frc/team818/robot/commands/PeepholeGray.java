package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;
import org.usfirst.frc.team818.robot.utilities.VisionUtilities;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Range;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;


public class PeepholeGray extends CommandBase {
	
	private NIVision.Image colorImage, grayImage;
	private double rotateValue;
	
	public PeepholeGray() {
		requires(peephole);
		colorImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		grayImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
	}
	
	protected void initialize() {
		
		try {
			colorImage = peephole.fetchImage();
			rotateValue = VisionUtilities.getGoalAngle(colorImage);
		} catch (VisionException ve) {
			RobotLog.putMessage("Error fetching image!");
			return;
		}
		
		NIVision.imaqColorThreshold(grayImage, colorImage, 255, NIVision.ColorMode.HSV, new Range(100, 140), new Range(130, 255), new Range(140, 220));
		RobotLog.putMessage("Angle should be: " + rotateValue);
		
		CameraServer.getInstance().setImage(grayImage);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
	}
	
	protected void interrupted() {
	}
	
}
