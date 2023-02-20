// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DC;

public class Drivetrain extends SubsystemBase {
  // Power Distribution Hub (To Clear Sticky Faults)
  private static PowerDistribution pd = new PowerDistribution();
  // Motors
  private final WPI_TalonFX frontRight = new WPI_TalonFX(DC.FR_TALONFX); // Front Right Motor
  private final WPI_TalonFX backRight = new WPI_TalonFX(DC.BR_TALONFX); // Back Right Motor
  private final WPI_TalonFX middleRight = new WPI_TalonFX(DC.MR_TALONFX); // Middle Right Motor
  private final WPI_TalonFX frontLeft = new WPI_TalonFX(DC.FL_TALONFX); // Front Left Motor
  private final WPI_TalonFX backLeft = new WPI_TalonFX(DC.BL_TALONFX); // Back Left Motor
  private final WPI_TalonFX middleLeft = new WPI_TalonFX(DC.ML_TALONFX); // Middle Left Motor
  // Motor Controller Groups
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight, middleRight); // Right Motors
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft, middleLeft); // Left Motors
  // Differential Drive
  private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors); // Differential Drive
  // Pigeon 2.0 IMU
  private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(DC.P_PIGEON); // Pigeon 2.0 IMU
  private NetworkTableEntry pitchEntry; // Pitch Entry
  public double pitchValue; // Pitch Value
  // Current Limits (Falcon 500s)
  void setFalconLimit(WPI_TalonFX falcon) // Set Current Limit (Falcon 500s)
  {
    // Supply = 40A (Breaker Limit). Use this to prevent browning.
    falcon.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 39, 40, 10));
    // Stator = 90A (Falcon 500 Limit). Use this to prevent Motor Burnout.
    falcon.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 70, 90, 1));
  }
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    pd.clearStickyFaults(); // Clear Sticky Faults (Power Distribution Hub)
    setFalconLimit(frontRight); // Set Current Limit (Front Right Falcon 500)
    setFalconLimit(backRight); // Set Current Limit (Back Right Falcon 500)
    setFalconLimit(middleRight); // Set Current Limit (Middle Right Falcon 500)
    setFalconLimit(frontLeft); // Set Current Limit (Front Left Falcon 500)
    setFalconLimit(backLeft); // Set Current Limit (Back Left Falcon 500)
    setFalconLimit(middleLeft); // Set Current Limit (Middle Left Falcon 500)
    rightMotors.setInverted(true); // Invert Right Motors
    NetworkTableInstance inst = NetworkTableInstance.getDefault(); // Get Network Table Instance
    NetworkTable table = inst.getTable("DriveData"); // Get DriveData Table
    pitchEntry = table.getEntry("Pitch"); // Get Pitch Entry
    setCoast(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double pitch = m_pigeon.getPitch(); // Get Pitch
    pitchEntry.setDouble(pitch); // Set Pitch
    onSlope();
    if (onSlope() == true) {
      setBrake(NeutralMode.Brake);
    } else {
      setCoast(NeutralMode.Coast);
    }
  }
  // onSlope Query
  public boolean onSlope() {
    return pitchValue < DC.MINSEEKPITCH || pitchValue > DC.MAXSEEKPITCH;
  }
  // Arcade Drive Function
  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }
  // Tank Drive Function
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    drive.tankDrive(leftVolts, rightVolts);
    drive.feed();
  }
  // Set Brake Mode
  public void setBrake(NeutralMode mode) {
    frontRight.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    middleRight.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    middleLeft.setNeutralMode(NeutralMode.Brake);
  }
  // Set Coast Mode
  public void setCoast(NeutralMode mode) {
    frontRight.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);
    middleRight.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    middleLeft.setNeutralMode(NeutralMode.Coast);
  }
}
