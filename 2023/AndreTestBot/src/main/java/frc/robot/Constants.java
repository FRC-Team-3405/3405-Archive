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
  public static final int FR_TALONFX = 2; // Front Right TalonFX
  public static final int BR_TALONFX = 3; // Back Right TalonFX
  public static final int FL_TALONFX = 4; // Front Left TalonFX
  public static final int BL_TALONFX = 5; // Back Left TalonFX
  public static final double MINPITCH = -5.0; // Minimum Pitch to Activate Brake Mode
  public static final double MAXPOWER = 0.8; // Maximum motor output (80%)
  // Pigeon 2.0 IMU
  public static final int P_PIGEON = 6; // Pigeon 2.0 IMU

  /** Intake Constants */
  public static final int INTAKE_SRX = 7; // Intake TalonSRX
  public static final int INTAKE_IN = 6;
  public static final int INTAKE_OUT = 7;

  /** Shooter Constants */
  public static final int RS_TALONFX = 8; // Right Shooter TalonFX
  public static final int LS_TALONFX = 9; // Left Shooter TalonFX
  public static final int LTM_TALONSRX = 10; // Lower Tower Motor TalonSRX
  public static final int UTM_TALONSRX = 11; // Upper Tower Motor TalonSRX
  public static final int STM_VICTORSPX = 12; // Intake VictorSPX

  /** Limelight Constants */
  public static final double KNOWN_DISTANCE = 3.048; // 10 Feet (120 inches in meters)
  public static final int PIXEL_WIDTH_KNOWN = 60;
  public static final double KNOWN_TAPE_BOUND_WIDTH = 0.3937; // Width of the tape (15.5 inches converted to meters)
  public static final double FOCAL_LENGTH = (KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH; //** This is an equation of some kind pulled from this repo: https://github.com/Yeti-Robotics/Y3T1-java-2021/blob/master/src/main/java/frc/robot/RobotContainer.java */
  public static final int WIDTH = 960;
  public static final int HEIGHT = 720;
  public static final double FOV_SUB_H = 59.6;
  public static final double FOV_SUB_V = 45.7;
  /** Operator Constants */
  public static class OperatorConstants {
    public static final int P_XBOX = 0; // Controller Port
    public static final int XBOX_XAXIS = 1; // X Axis on Xbox Controller
    public static final int XBOX_YAXIS = 4; // Y Axis on Xbox Controller
  }
}
