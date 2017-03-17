package org.usfirst.frc.team818.robot.utilities;

import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Range;

import edu.wpi.first.wpilibj.util.SortedVector.Comparator;

public class VisionUtilities {
	
	private static final double FOV = 55;
	private static final double MIN_AREA = 5.0;
	private static final Range HUE_RANGE = new Range(100, 140);
	private static final Range SAT_RANGE = new Range(130, 255);
	private static final Range VAL_RANGE = new Range(140, 220);
	
	private static NIVision.Image colorImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	private static NIVision.Image grayImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
	private static NIVision.ParticleFilterCriteria2[] criteria = {new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, MIN_AREA, 100.0, 0, 0)};
	private static NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	
	public static class ParticleReport implements Comparator, Comparable<ParticleReport> {
		
		double ConvexHullArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		
		public int compareTo(ParticleReport r) {
			return (int) (r.ConvexHullArea - this.ConvexHullArea);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2) {
			return (int) (r1.ConvexHullArea - r2.ConvexHullArea);
		}
		
		public int compare(Object object1, Object object2) {
			return (int) (((ParticleReport) object1).ConvexHullArea - ((ParticleReport) object2).ConvexHullArea);
		}
	};
	
	public static double getGoalAngle(NIVision.Image image) {
		
		colorImage = image;
		NIVision.imaqColorThreshold(grayImage, colorImage, 255, NIVision.ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);
		NIVision.imaqParticleFilter4(grayImage, grayImage, criteria, filterOptions, null);
		int particleCount = NIVision.imaqCountParticles(grayImage, 1);
		
		RobotLog.putMessage("Found filtered particles: " + particleCount);
		
		if (particleCount > 0) {
			
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			
			for (int i = 0; i < particleCount; i++) {
				ParticleReport report = new ParticleReport();
				report.ConvexHullArea = NIVision.imaqMeasureParticle(grayImage, i, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
				report.BoundingRectLeft = NIVision.imaqMeasureParticle(grayImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				report.BoundingRectRight = NIVision.imaqMeasureParticle(grayImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				report.BoundingRectTop = NIVision.imaqMeasureParticle(grayImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				report.BoundingRectBottom = NIVision.imaqMeasureParticle(grayImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
			}
			particles.sort(null);
			
			int imageWidth = NIVision.imaqGetImageSize(image).width;
			return angleFromReport(particles.elementAt(0), imageWidth);
			
		} else {
			return 0;
		}
	}
	
	private static double angleFromReport(ParticleReport report, int imageWidth) {
		
		double goalX = (report.BoundingRectRight + report.BoundingRectLeft) / 2;
		double dToCenter = Math.abs((imageWidth / 2) - goalX);
		double dViewToScreen = (imageWidth / 2) / Math.tan(Math.toRadians(FOV / 2));
		double returnAngle = Math.toDegrees(Math.atan(dToCenter / dViewToScreen));
		
		return returnAngle * ((goalX > (imageWidth / 2)) ? 1 : -1);
		
	}
	
}
