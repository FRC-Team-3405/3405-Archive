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
  /** Drivetrain Constants */
  // Pneumatics
  public static final int HIGHGEAR = 2; // High Gear Solenoid
  public static final int LOWGEAR = 3; // Low Gear Solenoid
  // Motors
  public static final int FR_TALONFX = 1; // Front Right TalonFX
  public static final int BR_TALONFX = 2; // Back Right TalonFX
  public static final int FL_TALONFX = 3; // Front Left TalonFX
  public static final int BL_TALONFX = 4; // Back Left TalonFX
  public static final int MINPITCH = 15;
  // Pigeon 2.0 IMU
  public static final int P_PIGEON = 14; // Pigeon 2.0 IMU

  /** Intake Constants */
  public static final int INTAKE_SRX = 10; // Intake TalonSRX
  public static final int INTAKE_IN = 6;
  public static final int INTAKE_OUT = 7;

  /** Shooter Constants */
  public static final int RS_TALONFX = 20; // Right Shooter TalonFX
  public static final int LS_TALONFX = 21; // Left Shooter TalonFX
  public static final int LTM_TALONSRX = 5; // Lower Tower Motor TalonSRX
  public static final int UTM_TALONSRX = 6; // Upper Tower Motor TalonSRX
  public static final int STM_VICTORSPX = 7; // Intake VictorSPX
  /** Operator Constants */
  public static class OperatorConstants {
    public static final int P_XBOX = 0; // Controller Port
    public static final int XBOX_XAXIS = 1; // X Axis on Xbox Controller
    public static final int XBOX_YAXIS = 3; // Y Axis on Xbox Controller
  }
}
