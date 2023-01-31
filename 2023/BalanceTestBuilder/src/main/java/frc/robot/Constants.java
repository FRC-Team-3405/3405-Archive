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
  /* DRIVETRAIN CONSTANTS */
  // Pneumatics
  public static final int HIGHGEAR = 2; // High Gear Port
  public static final int LOWGEAR = 3; // Low Gear Port
  // Motors
  public static final int FR_TALONFX = 2; // Front Right Talon FX Port
  public static final int BR_TALONFX = 3; // Back Right Talon FX Port
  public static final int FL_TALONFX = 4; // Front Left Talon FX Port
  public static final int BL_TALONFX = 5; // Back Left Talon FX Port
  public static final double MAXPOWER = 0.8; // Percent
  public static final int AUTOSPEED = 4; // VOLTS; Speed for driving on flat surfaces during autonomous
  public static final int AUTOBALANCESPEED = 2; // VOLTS; Speed for driving on slopes during autonomous
  // Pigeon 2.0 IMU
  public static final int P_PIGEON = 6; // Pigeon 2.0 Port
  public static final double MINPITCH = -7.0; // Degrees
  public static final double MAXPITCH = 7.0; // Degrees
  public static final double MINBALANCEPITCH = -10.0; // Degrees
  public static final double MAXBALANCEPITCH = 10.0; // Degrees

  public static class OperatorConstants {
    public static final int P_XBOX = 0; // Controller Port
    public static final int XAXIS = 1; // X Axis on Xbox Controller
    public static final int YAXIS = 4; // Y Axis on Xbox Controller
  }
}
