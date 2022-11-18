// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Note: 'M' = Motor, 'R' = RIO Port, 'B' = Boolean, 'V' = Calculated Value
    // DRIVETRAIN //
    // - DriveTrain Motors & Shifter Ports - //
    public static int M_FR = 1; // Front-right Falcon500 Motor
    public static int M_BR = 2; // Back-right Falcon500 Motor
    public static int M_FL = 3; // Front-left Falcon500 Motor
    public static int M_BL = 4; // Back-left Falcon500 Motor
    public static final int P_HIGHGEAR = 2; // Shifter Solenoid kForward Port
    public static final int P_LOWGEAR = 3; // Shifter Solenoid kReverse Port
    // -- DriveTrain Encoders & Values -- //
    public static int[] R_RIGHTENCODER = {0,1}; // Right Encoder Ports
    public static int[] R_LEFTENCODER = {2,3}; // Left Encoder Ports
    public static boolean B_RIGHTREVERSE = true; // Reverse the Right Encoder value
    public static boolean B_LEFTREVERSE = false; // Do not reverse the Left Encoder Value
    public static final double V_ENCODERREZ = 2048.0; 
    public static final double V_DISTANCEPERPULSE = 1/8192 * .1524 * Math.PI; // Autonomous Routine Value
    // -- DriveTrain Values -- //
    public static final double S_MAXPOWER = 0.8; // Max Power to the motors during Teleop
    public static final double S_MAXTURNPOWER = 0.8; // Max Turning Power during Teleop
    public static final double V_VOLTS = 0.53732; // Speed
    public static final double V_V_VOLTS = 5.5; // Velocity
    public static final double V_A_VOLTS = 0.44883; // Acceleration
    public static final double V_KINEMATICS = 0.65836; // Width between the wheels (Meters)

    // SHOOTER //
    public static final double V_SHOOTERHEIGHT = 0.635; // Shooter height off the ground (Meters)
    public static final double V_GRAVITY = 9.81; // Meters/sec^2

    // DRIVER CONTROLLER //
    public static final int CTRLPORT = 0; // Xbox Controller Port
    public static final int X_XAXIS = 1; // Forward Axis
    public static final int X_YAXIS = 4; // Rotate Axis

}
