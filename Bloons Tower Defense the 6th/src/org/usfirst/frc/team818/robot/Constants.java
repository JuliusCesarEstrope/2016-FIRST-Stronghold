package org.usfirst.frc.team818.robot;

public class Constants {
	
	// enable/disable subsystems
	public static final boolean wagonEnabled = true;
	public static final boolean lanternEnabled = false;
	public static final boolean peepholeMainEnabled = false;
	public static final boolean peepholeShootEnabled = true;
	public static final boolean drawbridgeEnabled = true;
	public static final boolean ballistaEnabled = false;
	public static final boolean peasantEnabled = true;
	public static final boolean tankSwapEnabled = true;
	
	// Ports for the joysticks
	public static final int leftJoystickPort = 0;
	public static final int rightJoystickPort = 1;
	public static final int controllerPort = 2;
	
	// Drive motor ports
	public static final int[] wagonLeftMotorPorts = { 3, 4, 7 };
	public static final int[] wagonRightMotorPorts = { 1, 2, 5 };
	public static final int wagonGyroPort = 0;
	
	// Drawbridge motor and snesor ports
	public static final int drawbridgeLiftMotorPort = 6;
	private static final int drawbridgeInnerLimitPort = 0;
	private static final int drawbridgeOuterLimitPort = 1;
	public static final int[] drawbridgeLimitPorts = { drawbridgeInnerLimitPort, drawbridgeOuterLimitPort };
	public static final int drawbridgeGyroPort = 1;
	
	// Ballista motor ports
	public static final int ballistaMotorPort = 8;
	public static final int ballistaRPMSensorPort = 5;
	
	// Peasant motor ports
	public static final int[] peasantMotorPorts = { 9 };
	
	private static final int redLEDPort = 1;
	private static final int greenLEDPort = 0;
	private static final int blueLEDPort = 2;
	public static final int[] ledPorts = { redLEDPort, greenLEDPort, blueLEDPort };
	
	public static final String peepholeMainName = "cam0";
	public static final String peepholeShootName = "cam1";
	
	public static final String[] defenseAutonomi = { "LowBarAuton",
													 "GenericDefenseAuton",
													 "PortcullisAuton",
													 "ChevalDeFriseAuton" };
	
}