package org.usfirst.frc.team818.robot;

import org.usfirst.frc.team818.robot.commands.CommandBase;
import org.usfirst.frc.team818.robot.utilities.AutonomousSelector;
import org.usfirst.frc.team818.robot.utilities.RobotLog;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotMain extends IterativeRobot {
	
	private Command autonomous;
	
	public void robotInit() {
		RobotLog.init();
		CommandBase.init();
		RobotLog.putMessage("Initialized successfully; let's play some Stronghold!");
	}
	
	public void autonomousInit() {
		autonomous = AutonomousSelector.getSelectedAutonomous();
		if (autonomous != null)
			autonomous.start();
	}
	
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		if (autonomous != null)
			autonomous.cancel();
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void disabledInit() {
		CommandBase.disable();
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
}
