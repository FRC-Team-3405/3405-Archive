// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot; // Don't touch this line. It's used by the program to know build your robot program.

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

/**
 * This is the class where we can store important numbers for later use. We can also store important
 * calculation results in here, so we don't have to read in calculations from multiple files. The point
 * of command-based programming is to organize code in a way that makes it easy to read and understand.
 */

public final class Constants {
    //** (NOTE: Organize, organize, organize!) **//

    //** DRIVETRAIN CONSTANTS */ 
    public static final int FR_DRIVEMOTOR = 1; // Front Right Drivetrain Motor Port
    public static final int BR_DRIVEMOTOR = 2; // Back Right Drivetrain Motor Port
    public static final int FL_DRIVEMOTOR = 3; // Front Left Drivetrain Motor Port
    public static final int BL_DRIVEMOTOR = 4; // Back Left Drivetrain Motor Port

    public static final double MAX_DRIVE_SPEED = 0.8; // Maximum moving speed of the DriveTrain motors (in percent)
    public static final double MAX_DRIVE_TURNING_SPEED = 0.7; // Maximum turning speed of the DriveTrain motors (in percent)

    //** PNEUMATICS CONSTANTS */
    public static final int PCM_PORT = 0; // CTRE Pneumatics Control Module Port
    public static final int COMPRESSOR_PORT = 0; // Compressor Port
    public static final int HIGHGEAR_SOLENOID_PORT = 0; // High Gear Solenoid Port
    public static final int LOWGEAR_SOLENOID_PORT = 1; // Low Gear Solenoid Port
}
