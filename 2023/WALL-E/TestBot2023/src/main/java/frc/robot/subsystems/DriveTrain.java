// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX; // Import TalonFX Library
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration; // Stator Current Limit
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration; // Supply Current Limit
import com.ctre.phoenix.sensors.WPI_Pigeon2; // Import Pigeon 2.0 Library
import edu.wpi.first.wpilibj.DoubleSolenoid; // Shifter Solenoid
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveTrain extends SubsystemBase {
  /* Pneumatics */
  public static DoubleSolenoid m_shift = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.HIGHPORT, Constants.LOWPORT); // Shifter Solenoid
  public static boolean LowGear = false;
  /* Motors */
  private final WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.FL_TALONFX); // Front Left TalonFX
  private final WPI_TalonFX backLeft = new WPI_TalonFX(Constants.BL_TALONFX); // Back Left TalonFX
  private final WPI_TalonFX frontRight = new WPI_TalonFX(Constants.FR_TALONFX); // Front Right TalonFX
  private final WPI_TalonFX backRight = new WPI_TalonFX(Constants.BR_TALONFX); // Back Right TalonFX
  /* Motor Controller Groups */
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(frontLeft, backLeft); // Left Motor Group
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(frontRight, backRight); // Right Motor Group
  /* Differential Drive */
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors); // Differential Drive
  /* Pigeon 2.0 */
  private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(Constants.PIGEONPORT); // Pigeon 2.0
  /* Current Limit */
  void setFalconLimit(WPI_TalonFX talon) {
    talon.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 70, 90, 1)); // Stator Current Limit
    talon.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 39, 40, 10)); // Supply Current Limit
  }
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    setFalconLimit(frontLeft); // Set Front Left Falcon Limit
    setFalconLimit(backLeft); // Set Back Left Falcon Limit
    setFalconLimit(frontRight); // Set Front Right Falcon Limit
    setFalconLimit(backRight); // Set Back Right Falcon Limit
    m_shift.set(Value.kForward); // Set Shifter Solenoid to High Gear
    m_rightMotors.setInverted(true); // Set Right Motors to Inverted
  }

  public void shiftHigh() {
    m_shift.set(Value.kForward); // Set Shifter Solenoid to High Gear
  }

  public void shiftLow() {
    m_shift.set(Value.kReverse); // Set Shifter Solenoid to Low Gear
  }

  public void shift() {
    m_shift.toggle();
    LowGear = !LowGear;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }
}
