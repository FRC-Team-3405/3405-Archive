// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  public final WPI_TalonFX frontRight = new WPI_TalonFX(2);
  public final WPI_TalonFX backRight = new WPI_TalonFX(3);
  public final WPI_TalonFX frontLeft = new WPI_TalonFX(4);
  public final WPI_TalonFX backLeft = new WPI_TalonFX(5);
  public final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight);
  public final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft);
  public final DifferentialDrive m_drive = new DifferentialDrive(rightMotors, leftMotors);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
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
