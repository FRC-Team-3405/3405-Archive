// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends SubsystemBase {
  public final WPI_TalonSRX m_leftmotor = new WPI_TalonSRX(Constants.M_LEFT);
  public final WPI_TalonSRX m_rightmotor = new WPI_TalonSRX(Constants.M_RIGHT);

  private final DifferentialDrive drive = new DifferentialDrive(m_leftmotor, m_rightmotor);
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_rightmotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftmotor.setVoltage(leftVolts);
    m_rightmotor.setVoltage(rightVolts);
    m_leftmotor.feed();
    m_rightmotor.feed();
  }
}
