package org.usfirst.frc.team818.robot.utilities;

import org.usfirst.frc.team818.robot.Constants;
import org.usfirst.frc.team818.robot.autonomi.*;
import org.usfirst.frc.team818.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutonomousSelector {
	
	public static Command getSelectedAutonomous() {
		
		int defenseIndex, positionIndex, actionIndex;
		
		try {
			
			defenseIndex = Integer.parseInt(SmartDashboard.getString("Auton selector 1"));
			positionIndex = Integer.parseInt(SmartDashboard.getString("Auton selector 2"));
			actionIndex = Integer.parseInt(SmartDashboard.getString("Auton selector 3"));
			
		} catch (Exception exc) {
			
			RobotLog.putMessage("Couldn't find the autonomous");
			RobotLog.putMessage(exc.getMessage());
			defenseIndex = 1;
			positionIndex = 2;
			actionIndex = 0;
			
		}
		
		Command autonomous = constructAuton(defenseIndex, positionIndex, actionIndex);
		return autonomous;
		
	}
	
	private static Command constructAuton(int defenseIndex, int positionIndex, int actionIndex) {
		
		CommandGroup returnCommand = new CommandGroup();
		
		try {
			
			if (actionIndex == 0) {
				
				returnCommand.addSequential(new DoNothingAuton());
				
			} else {
				
				int basicDefenseIndex = 0;
				if (defenseIndex >= 1 && defenseIndex <= 4) basicDefenseIndex = 1;
				if (defenseIndex == 5) basicDefenseIndex = 2;
				if (defenseIndex == 6) basicDefenseIndex = 3;
				
				Class<?> defenseClass = Class.forName("org.usfirst.frc.team818.robot.autonomi." + Constants.defenseAutonomi[basicDefenseIndex]);
				returnCommand.addSequential(new DrawbridgeResetGyro());
				returnCommand.addSequential((CommandGroup)defenseClass.newInstance());
				returnCommand.addSequential(new WagonRotateToGyro());
				
				if (actionIndex == 1) {
					
					// Ignore the rest
					
				} else if (actionIndex == 2) {
					
					returnCommand.addSequential(new WagonRotate(180));
					
				} else {
					
					double[] turnAngle1 = { 0, 0, -20, 15, 0 };
					double[] turnAngle2 = { -90, -90, -90, 90, 90 };
					double[] driveTime = { 1.8, 1, 0.5, 0.5, 1 };
					
					returnCommand.addSequential(new WagonRotate(turnAngle1[positionIndex]));
					returnCommand.addSequential(new WagonStraightForTime(-0.4, 1.2));
					returnCommand.addSequential(new WagonRotate(turnAngle2[positionIndex]));
					//returnCommand.addSequential(new WagonRotateToGoal());
					returnCommand.addSequential(new DrawbridgeMoveOut());
					returnCommand.addSequential(new WagonStraightForTime(0.5, driveTime[positionIndex]));
					
					if (actionIndex == 3) {
						returnCommand.addSequential(new ShootLowAuton());
					} else if (actionIndex == 4) {
						returnCommand.addSequential(new ShootHighAuton());
					}
					
				}
				
			}
			
		} catch (Exception e) {
			RobotLog.putMessage("Failed to initialize an autonomous:");
			RobotLog.putMessage(e.getMessage());
		}
		
		return returnCommand;
		
	}
	
}
