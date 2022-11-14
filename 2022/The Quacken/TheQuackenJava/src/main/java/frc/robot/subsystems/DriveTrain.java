// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends SubsystemBase {
  // DriveTrain
  // Left Motors
  private final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(Constants.FL_TALONSRX);
  private final WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(Constants.BL_TALONSRX);
  // Right Motors
  private final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(Constants.FR_TALONSRX);
  private final WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(Constants.BR_TALONSRX);
  // Motor Controller Groups
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftFrontMotor, leftBackMotor); // Left Motors
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightFrontMotor, rightBackMotor); // Right Motors
  // The Robot's Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors);

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    rightMotors.setInverted(true);
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(rightVolts);
    m_drive.feed();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
