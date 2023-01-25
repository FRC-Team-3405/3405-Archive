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
  /* Drivetrain */
  public static final int FR_TALONSRX = 1; // Front Right Motor
  public static final int BR_TALONSRX = 2; // Back Right Motor
  public static final int FL_TALONSRX = 3; // Front Left Motor
  public static final int BL_TALONSRX = 4; // Back Left Motor
  /* Pneumatics */
  public static final int P_HIGHGEAR = 4; // High Gear Port
  public static final int P_LOWGEAR = 5; // Low Gear Port
  /* Pigeon2.0 */
  public static final int P_PIGEON = 5;
  /* Operator Interface */
  public static class OperatorConstants {
    public static final int P_JOYSTICK = 0;
    public static final int XAXIS = 1;
    public static final int YAXIS = 0;
  }
}
