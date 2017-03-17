package org.usfirst.frc.team818.robot.utilities;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;


public class RPMSensor implements PIDSource {
	
	private boolean calculating;
	
	private DigitalInput sensor;
	private ArrayList<Double> rpmsList;
	private Runnable getRPMs;
	private Timer timer;
	
	public RPMSensor(int port) {
		
		calculating = false;
		
		sensor = new DigitalInput(port);
		rpmsList = new ArrayList<>(0);
		timer = new Timer();
		getRPMs = () -> {
			
			boolean lastValue = sensor.get();
			int currentRevolutions = 0;
			
			timer.start();
			
			while (calculating) {
				if (lastValue ^ sensor.get()) {
					if (sensor.get()) {
						currentRevolutions++;
					}
					lastValue = sensor.get();
				}
				if (timer.hasPeriodPassed(0.5)) {
					double rpm = currentRevolutions * 120.0;
					currentRevolutions = 0;
					rpmsList.add(rpm);
					if (rpmsList.size() > 8)
						rpmsList.remove(0);
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException ie) {
					RobotLog.putMessage("Something went terribly wrong with the RPM sensor");
				}
			}
			
			timer.stop();
			
		};
	}
	
	public void start() {
		calculating = true;
		Thread getRPMsThread = new Thread(getRPMs);
		getRPMsThread.start();
	}
	
	public void stop() {
		calculating = false;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	}
	
	@Override
	public double pidGet() {
		double sumRPM = 0;
		double averageRPM = 0;
		for (int i = 0; i < rpmsList.size(); i++) {
			sumRPM += rpmsList.get(i);
		}
		averageRPM = sumRPM / rpmsList.size();
		return averageRPM;
	}
	
}
