package org.usfirst.frc.team818.robot.subsystems;

import org.usfirst.frc.team818.robot.commands.PeepholeUpdate;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;


public class PeepholeSubsystem extends Subsystem {
	
	private boolean peepholeMainEnabled, peepholeShootEnabled, mainFacing;
	private int mainSession;
	
	private USBCamera shootCamera;
	private Image image;
	
	public PeepholeSubsystem(String mainName, String shootName, boolean peepholeMainEnabled, boolean peepholeShootEnabled) {
		
		this.peepholeMainEnabled = peepholeMainEnabled;
		this.peepholeShootEnabled = peepholeShootEnabled;
		
		image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		try {
			
			if (peepholeShootEnabled) {
				mainFacing = false;
				shootCamera = new USBCamera(shootName);
				// shootCamera.setBrightness(5);
				RobotLog.putMessage("The shooting peephole is cleared for aiming");
			}
			if (peepholeMainEnabled) {
				mainFacing = true;
				mainSession = NIVision.IMAQdxOpenCamera(mainName, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
				RobotLog.putMessage("The main peephole is cleared for spying");
			}
			
		} catch (Exception e) {
			RobotLog.putMessage("Tried initializing the camera, but an error occured:");
			RobotLog.putMessage(e.getMessage());
		}
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new PeepholeUpdate());
	}
	
	public Image fetchImage() throws VisionException {
		if (peepholeMainEnabled || peepholeShootEnabled) {
			if (mainFacing) {
				NIVision.IMAQdxGrab(mainSession, image, 1);
			} else {
				shootCamera.getImage(image);
			}
			return image;
		} else {
			throw new VisionException("Camera is not enabled");
		}
	}
	
	public String getFacing() {
		if (peepholeMainEnabled || peepholeShootEnabled) {
			return (mainFacing) ? "Main" : "Shoot";
		} else {
			return "";
		}
	}
	
	public void swapView() {
		if (peepholeMainEnabled && peepholeShootEnabled) {
			mainFacing = !mainFacing;
			if (mainFacing) {
				shootCamera.stopCapture();
				NIVision.IMAQdxConfigureGrab(mainSession);
				NIVision.IMAQdxStartAcquisition(mainSession);
			} else {
				NIVision.IMAQdxStopAcquisition(mainSession);
				shootCamera.startCapture();
			}
		}
	}
	
	public void start() {
		if (peepholeMainEnabled || peepholeShootEnabled) {
			if (mainFacing) {
				NIVision.IMAQdxConfigureGrab(mainSession);
				NIVision.IMAQdxStartAcquisition(mainSession);
			} else {
				shootCamera.startCapture();
			}
		}
	}
	
	public void stop() {
		if (peepholeMainEnabled) {
			NIVision.IMAQdxStopAcquisition(mainSession);
		}
		if (peepholeShootEnabled) {
			shootCamera.stopCapture();
		}
	}
	
	public void setEnabled(boolean enabled) {
		peepholeMainEnabled = enabled;
		peepholeShootEnabled = enabled;
	}
	
}
