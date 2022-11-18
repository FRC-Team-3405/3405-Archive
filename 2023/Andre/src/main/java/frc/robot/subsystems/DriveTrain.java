// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveTrain extends SubsystemBase {
  // Motors
  public final WPI_TalonFX frontRight = new WPI_TalonFX(Constants.M_FR); // Front-right motor
  public final WPI_TalonFX backRight = new WPI_TalonFX(Constants.M_BR); // Back-right motor
  public final WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.M_FL); // Front-left motor
  public final WPI_TalonFX backLeft = new WPI_TalonFX(Constants.M_BL); // Back-left motor
  // Motor Controller Groups
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(frontRight, backRight); // Left Motors
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(frontLeft, backRight); // Right Motors
  // Differential Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_rightMotors, m_leftMotors);
  // Encoders
  private final Encoder rightEncoder = new Encoder(Constants.R_RIGHTENCODER[0], Constants.R_RIGHTENCODER[1], Constants.B_RIGHTREVERSE); // Right Encoder
  private final Encoder leftEncoder = new Encoder(Constants.R_LEFTENCODER[0], Constants.R_LEFTENCODER[1], Constants.B_LEFTREVERSE); // Left Encoder
  // Gyroscope
  private final Gyro gyro = new ADXRS450_Gyro(); // External Gyroscope Module
  // Pneumatics
  public static DoubleSolenoid m_shift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.P_HIGHGEAR, Constants.P_LOWGEAR); // Shifter mechanism
  public static boolean LowGear = false; // Start in High Gear
  // Voltage Constraints (Motors)
  void setFalconLimit(WPI_TalonFX talon) {
    talon.configSupplyCurrentLimit(
      new SupplyCurrentLimitConfiguration(true, 39, 40, 10) // Enabled, Current Limit, Trigger Threshold, Trigger Threshold Time
    );
    talon.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(true, 70, 90, 1.0)// Enabled, Current Limit, Trigger Threshold, Trigger Threshold Time
    );
  }
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    // Set Motor Limits (Per Motor)
    setFalconLimit(frontRight);
    setFalconLimit(backRight);
    setFalconLimit(frontLeft);
    setFalconLimit(backLeft);
    // Invert the right motor values so we don't spin
    m_rightMotors.setInverted(true);
    // Set the distance per pulse for the encoders
    rightEncoder.setDistancePerPulse(Constants.V_DISTANCEPERPULSE);
    leftEncoder.setDistancePerPulse(Constants.V_DISTANCEPERPULSE);
    // Shift to High Gear (Default)
    m_shift.set(Value.kForward);
    // Set the Encoder Values to 0 when enabled
    resetEncoders();
  }

  // Shift to High Gear; Update SmartDashboard to show we are in HIGH GEAR
  public void shiftHigh(){m_shift.set(Value.kForward);}

  // Shift to Low Gear; Update SmartDashboard to show we are in LOW GEAR
  public void shiftLow(){m_shift.set(Value.kReverse);}

  // Shift method
  public void shift() {
    m_shift.toggle();
    LowGear = !LowGear;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Return the current wheel speeds of the robot.
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
  }

  // Drive the robot using Arcade Drive controls.
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  // Control the left and right sides of the drive base directly with voltages
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(rightVolts);
    m_drive.feed();
  }

  // Reset the drive encoders to read a position of 0
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  // Get the value of the right drive encoder
  public double getRightEncoder() {
    return (rightEncoder.get() * Constants.V_DISTANCEPERPULSE);
  }
  
  // Get the value of the left drive encoder
  public double getLeftEncoder() {
    return (leftEncoder.get() * Constants.V_DISTANCEPERPULSE);
  }

  // Get the average distance of the two encoders
  public double getAverageEncoderDistance() {
    return (getLeftEncoder() + getRightEncoder() / 2);
  }

  // Set the max output of the drive base. This is useful for scaling the drive if necessary in Auto mode
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  // Return the turn rate of the robot
  public double getTurnRate() {
    return -gyro.getRate();
  }
}
