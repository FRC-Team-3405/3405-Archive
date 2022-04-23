// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// import com.ctre.phoenix.sensors.Pigeon2;

public class DriveTrain extends SubsystemBase {
  // DriveTrain
  // Left Motors
  private final WPI_TalonFX leftFrontMotor = new WPI_TalonFX(Constants.FL_TALONFX); // Front Left
  private final WPI_TalonFX leftBackMotor = new WPI_TalonFX(Constants.BL_TALONFX); // Back Left
  // Right Motors
  private final WPI_TalonFX rightFrontMotor = new WPI_TalonFX(Constants.FR_TALONFX); // Front Right
  private final WPI_TalonFX rightBackMotor = new WPI_TalonFX(Constants.BR_TALONFX);  // Back Right
  // Motor Controller Groups
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftFrontMotor, leftBackMotor);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightFrontMotor, rightBackMotor);
  // The Robot's Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors);

  // Pigeon 2.0
  // private final Pigeon2 m_pigeon = new Pigeon2(Constants.PIGEON_PORT);

  // TalonFX Current Limits
  void setFalconLimit(WPI_TalonFX talon) {
    talon.configSupplyCurrentLimit(
      new SupplyCurrentLimitConfiguration(true, 39, 40, 10)
    );
    talon.configStatorCurrentLimit(
      new StatorCurrentLimitConfiguration(true, 70, 90, 1.0)
    );
  }

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    // TalonFX Current Limits
    setFalconLimit(leftFrontMotor);
    setFalconLimit(leftBackMotor);
    setFalconLimit(rightFrontMotor);
    setFalconLimit(rightBackMotor);

    // Invert the right side motors
    rightMotors.setInverted(true);
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
