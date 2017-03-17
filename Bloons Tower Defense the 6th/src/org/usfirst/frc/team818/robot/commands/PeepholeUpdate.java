package org.usfirst.frc.team818.robot.commands;

import org.usfirst.frc.team818.robot.utilities.RobotLog;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.OverlayTextOptions;
import com.ni.vision.NIVision.Point;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;


public class PeepholeUpdate extends CommandBase {
	
	private NIVision.Image image;
	private int width, height;
	
	private Thread fetchThread = new Thread();
	
	public PeepholeUpdate() {
		requires(peephole);
	}
	
	protected void initialize() {
		peephole.start();
		try {
			image = peephole.fetchImage();
			width = NIVision.imaqGetImageSize(image).width;
			height = NIVision.imaqGetImageSize(image).height;
		} catch (VisionException ve) {
			width = 0;
			height = 0;
			RobotLog.putMessage("Something went wrong with vision initialization: " + ve.getMessage());
		}
	}
	
	protected void execute() {
		if (!fetchThread.isAlive()) {
			fetchThread = new Thread(() -> {
				try {
					NIVision.Image image = peephole.fetchImage();
					NIVision.imaqDrawLineOnImage(image, image, NIVision.DrawMode.DRAW_VALUE, new Point(width / 2, 0), new Point(width / 2, height), 16777215.0f);
					//NIVision.imaqDrawShapeOnImage(image, image, new Rect(0, 0, (width * 3) / 8, height / 12), NIVision.DrawMode.PAINT_VALUE, NIVision.ShapeMode.SHAPE_RECT, 0.0f);
					//NIVision.imaqOverlayText(image, new Point(4, 4), peephole.getFacing(), new RGBValue(255, 255, 255, 255), new OverlayTextOptions("Arial", 12, 0, 0, 0, 0, NIVision.TextAlignment.LEFT, NIVision.VerticalTextAlignment.TOP, new RGBValue(0, 0, 0, 0), 0), "Group1");
					//NIVision.imaqMergeOverlay(image, image, null, "Group1");
					CameraServer.getInstance().setImage(image);
				} catch (Exception e) {
					RobotLog.putMessage("ERROR GETTING IMAGE: " + e.getMessage());
					peephole.setEnabled(false);
				}
			});
			fetchThread.start();
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
	}
	
	protected void interrupted() {
	}
	
}
