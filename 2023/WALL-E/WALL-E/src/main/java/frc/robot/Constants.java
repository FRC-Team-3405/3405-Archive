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
  /* Pneumatics */
  public static final int HIGHGEAR = 2; // High Gear Port
  public static final int LOWGEAR = 3; // Low Gear Port
  public static final int ARM_OUT = 4; // Arm Out Port
  public static final int ARM_IN = 5; // Arm In Port
  //** DRIVETRAIN */
  /* Motors */
  public static final int FR_TALONFX = 2; // Front Right TalonFX
  public static final int BR_TALONFX = 3; // Back Right TalonFX
  public static final int FL_TALONFX = 4; // Front Left TalonFX
  public static final int BL_TALONFX = 5; // Back Left TalonFX
  public static final double MAXPOWER = 0.8; // Max Power (Percent)
  public static final double AUTOSPEED = 4.0; // VOLTS; Speed for driving on flat surfaces during autonomous
  public static final double AUTOBALANCESPEED = 2.0; // VOLTS; Speed for driving on slopes during autonomous
  public static final double SECONDARYBALANCESPEED = 1.5; // VOLTS; Speed for driving on slopes during autonomous
  public static final double TIMESPACE = 0.35; // Seconds
  // Pigeon 2.0 IMU
  public static final int P_PIGEON = 6; // Pigeon 2.0 Port
  public static final double MINSEEKPITCH = -7.0; // Degrees
  public static final double MAXSEEKPITCH = 7.0; // Degrees
  public static final double MINBALANCEPITCH = -9.75; // Degrees
  public static final double MAXBALANCEPITCH = 9.75; // Degrees
  public static final double MINSECONDARYBALANCE = -6.5; // Degrees
  public static final double MAXSECONDARYBALANCE = 6.5; // Degrees
  //** INTAKE ARM */
  // Motors
  public static final int NEO_ROT_RIGHT = 7; // NEO for rotating the intake arm
  public static final int NEO_ROT_LEFT = 8; // NEO for rotating the intake arm
  public static final int NEO_EXT_RIGHT = 9; // NEO for extending the intake arm
  public static final int NEO_EXT_LEFT = 10; // NEO for extending the intake arm
  // Breakbeam Sensor
  public static final int BREAKBEAMHIGH = 0; // Breakbeam Sensor High
  public static final int BREAKBEAMLOW = 1; // Breakbeam Sensor Low
  // Rotator PID Values
  public static final double ROTATOR_P = 0.06; // Rotator P Value
  public static final double ROTATOR_I = 0.0; // Rotator I Value
  public static final double ROTATOR_D = 0.08; // Rotator D Value
  public static final double ROTATOR_FF = 0.0; // Rotator F Value
  public static final double ROTATOR_I_ZONE = 0.5; // Rotator Tolerance
  // Extender PID Values
  public static final double EXTENDER_P = 0.06; // Extender P Value
  public static final double EXTENDER_I = 0.0; // Extender I Value
  public static final double EXTENDER_D = 0.08; // Extender D Value
  public static final double EXTENDER_FF = 0.0; // Extender F Value
  public static final double EXTENDER_I_ZONE = 0.5; // Extender Tolerance

  //** OPERATOR CONSTANTS */
  public static class OperatorConstants {
    // Controller Ports
    public static final int P_AIRFLO = 0;
    public static final int P_XBOX = 1;
    // DRIVER CONTROLLER CONSTANTS
    public static final int XAXIS = 1; // X Axis on Xbox Controller
    public static final int YAXIS = 4; // Y Axis on Xbox Controller
    // SECONDARY CONTROLLER CONSTANTS
  }
}
