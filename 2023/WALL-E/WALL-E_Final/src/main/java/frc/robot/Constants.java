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
  /**
   * DRIVETRAIN CONSTANTS
   */
  public static class DC {
    // CAN IDs
    public static final int FR_TALONFX = 2; // Front Right Falcon500
    public static final int BR_TALONFX = 3; // Back Right Falcon500
    public static final int MR_TALONFX = 4; // Middle Right Falcon500
    public static final int FL_TALONFX = 5; // Front Left Falcon500
    public static final int BL_TALONFX = 6; // Back Left Falcon500
    public static final int ML_TALONFX = 7; // Middle Left Falcon500
    public static final int P_PIGEON = 8; // Pigeon 2.0 IMU
    // Motor Performance Values
    public static final double MAXMOVEPOWER = 0.8; // Maximum Fwd/Back Power (Percent)
    public static final double MAXTURNPOWER = 0.6; // Maximum Rotating Power (Percent)
    public static final double AUTONAVSPEED = 3.5; // Maximum Moving Power (AUTO Flat Ground, VOLTS)
    public static final double AUTOBALANCESPEED = 2.0; // Maximum Moving Power (AUTO Slope, VOLTS)
    public static final double SECONDARYBALANCESPEED = 1.5; // Maximum Moving Power (AUTO Slope, VOLTS)
    // Pigeon 2.0 Performance Variables
    public static final double MINSEEKPITCH = -7.0; // Minimum Pitch (Degrees, Seeking the Slope)
    public static final double MAXSEEKPITCH = 7.0; // Maximum Pitch (Degrees, Seeking the Slope)
    public static final double MINBALANCEPITCH = -9.75; // Minimum Pitch (Degrees, While Balancing, 1st Time)
    public static final double MAXBALANCEPITCH = 9.75; // Maximum Pitch (Degrees, While Balancing, 1st Time)
    public static final double MINSECONDARYBALANCE = -6.5; // Minimum Pitch (Degrees, While Balancing, 2nd Time)
    public static final double MAXSECONDARYBALANCE = 6.5; // Maximum Pitch (Degrees, While Balancing, 2nd Time)
    // Timer Variables
    public static final double BALANCETIMESPACE = 0.35; // Pause for 0.35 Seconds (0.15s greater than loop time of 0.2s)
    }
  /** 
   * PNEUMATICS CONSTANTS
   */
  public static class PC {
    public static final int P_HIGHGEAR = 0;
    public static final int P_LOWGEAR = 1;
    public static final int P_CLAWCLOSED = 2;
    public static final int P_CLAWOPEN = 3;
    public static final int P_PRESSURESWITCH = 0;
    }
  /**
   * ARM CONSTANTS
   */
  public static class AC {
    // CAN BUS IDs
    public static final int ROT_RIGHT = 10;
    public static final int ROT_LEFT = 9;
    public static final int EXT_RIGHT = 11;
    public static final int EXT_LEFT = 12;
    // Breakbeam Sensor IDs
    public static final int BREAKBEAM_LEFT = 0;
    public static final int BREAKBEAM_RIGHT = 1;
    // Rotator Performance Values
    public static final double ROT_POWER = 0.3; // Rotator Power (Percent)
    // Extender Performance Values
    public static final double EXT_POWER = 0.5; // Extender Power (Percent)
    // Rotator PID Values
    public static final double ROT_P = 0.06; // Rotator P Value
    public static final double ROT_I = 0.0; // Rotator I Value
    public static final double ROT_D = 0.08; // Rotator D Value
    public static final double ROT_FF = 0.0; // Rotator F Value
    // Extender PID Values
    public static final double EXT_P = 0.06; // Extender P Value
    public static final double EXT_I = 0.0; // Extender I Value
    public static final double EXT_D = 0.08; // Extender D Value
    public static final double EXT_FF = 0.0; // Extender F Value
    // Rotator Positions
    public static final double DEF_ROT = 0.0; 
    public static final double ROT_ONE = 0.0;
    public static final double ROT_TWO = 2.0;
    public static final double ROT_THREE = 4.0;
    public static final double ROT_FOUR = 6.0;
    // Extender Positions
    public static final double DEF_EXT = 0.0;
    public static final double EXT_ONE = 0.0;
    public static final double EXT_TWO = 10.0;
    public static final double EXT_THREE = 20.0;
    public static final double EXT_FOUR = 30.0;
    // Auto Positions
    public static final double AUTO_ROT_POS = 4; // Auto Rotation Position
    public static final double AUTO_ROT_ADJ_POS = 6; // Auto Rotation Adjustment Position (Post-Scoring)
    public static final double AUTO_EXT_POS = 10; // Auto Extension Position
  }
  /**
   * OPERATOR CONSTANTS
   */
  public static class OperatorConstants {
    // Controller Ports
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    // Driver Controller Axes
    public static final int DRIVE_XAXIS = 1; // X Axis on Xbox Controller
    public static final int DRIVE_YAXIS = 3; // Y Axis on Xbox Controller
  }
}
