// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class DriveTrain extends SubsystemBase {
  public final WPI_TalonFX frontLeft = new WPI_TalonFX(20);
  public final WPI_TalonFX backLeft = new WPI_TalonFX(21);
  public final WPI_TalonFX frontRight = new WPI_TalonFX(22);
  public final WPI_TalonFX backRight = new WPI_TalonFX(23);
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight);

  private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    rightMotors.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }
}
