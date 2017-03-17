package org.usfirst.frc.team818.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team818.robot.Constants;
import org.usfirst.frc.team818.robot.OI;
import org.usfirst.frc.team818.robot.subsystems.*;
import org.usfirst.frc.team818.robot.utilities.RobotLog;


public abstract class CommandBase extends Command {
	
	public static OI oi;
	
	public static WagonSubsystem wagon;
	public static LanternSubsystem lantern;
	public static PeepholeSubsystem peephole;
	public static DrawbridgeSubsystem drawbridge;
	public static BallistaSubsystem ballista;
	public static PeasantSubsystem peasant;
	
	public static void init() {
		
		wagon = new WagonSubsystem(Constants.wagonLeftMotorPorts, Constants.wagonRightMotorPorts, Constants.wagonGyroPort, Constants.wagonEnabled);
		lantern = new LanternSubsystem(Constants.ledPorts, Constants.lanternEnabled);
		peephole = new PeepholeSubsystem(Constants.peepholeMainName, Constants.peepholeShootName, Constants.peepholeMainEnabled, Constants.peepholeShootEnabled);
		drawbridge = new DrawbridgeSubsystem(Constants.drawbridgeLiftMotorPort, Constants.drawbridgeLimitPorts, Constants.drawbridgeGyroPort, Constants.drawbridgeEnabled);
		ballista = new BallistaSubsystem(Constants.ballistaMotorPort, Constants.ballistaRPMSensorPort, Constants.ballistaEnabled);
		peasant = new PeasantSubsystem(Constants.peasantMotorPorts, Constants.peasantEnabled);
		
		RobotLog.putMessage("All subsystems initialized successfully . . . probably");
		
		oi = new OI();
		
	}
	
	public static void disable() {
		wagon.set(0, 0);
		drawbridge.setSpeed(0);
		peasant.setSpeed(0);
		//peephole.stop();
		ballista.setSpeed(0);
	}
}
