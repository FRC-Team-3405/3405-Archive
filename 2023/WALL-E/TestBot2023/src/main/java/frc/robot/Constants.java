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
  /* DriveTrain */
  // Motor Ports
  public static final int FL_TALONFX = 1;
  public static final int BL_TALONFX = 2;
  public static final int FR_TALONFX = 3;
  public static final int BR_TALONFX = 4;
  // Pneumatic Ports
  public static final int HIGHPORT = 0;
  public static final int LOWPORT = 1;
  // Pigeon 2.0 Port
  public static final int PIGEONPORT = 5;

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int D_XAXIS = 1;
    public static final int D_YAXIS = 0;
  }
}
