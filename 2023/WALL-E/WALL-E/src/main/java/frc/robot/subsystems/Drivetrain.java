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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /* Pneumatics */
  public static DoubleSolenoid m_shift = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.LOWGEAR, Constants.HIGHGEAR); // Shifter Solenoid
  public static boolean HighGear = false; // High Gear is true, Low Gear is false
  /* Drivetrain */
  // Motors
  private final WPI_TalonFX frontRight = new WPI_TalonFX(Constants.FR_TALONFX); // Front Right TalonFX
  private final WPI_TalonFX backRight = new WPI_TalonFX(Constants.BR_TALONFX); // Back Right TalonFX
  private final WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.FL_TALONFX); // Front Left TalonFX
  private final WPI_TalonFX backLeft = new WPI_TalonFX(Constants.BL_TALONFX); // Back Left TalonFX
  // Motor Controller Groups
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight); // Right Motors
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft); // Left Motors
  // Differential Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors); // Differential Drive
  // Pigeon 2.0 IMU
  private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(Constants.P_PIGEON); // Pigeon 2.0 IMU
  private NetworkTableEntry yawEntry; // Yaw Entry
  private NetworkTableEntry pitchEntry; // Pitch Entry
  private NetworkTableEntry rollEntry; // Roll Entry
  public double pitchValue; // Pitch Value
  // Current Limits
  void setFalconLimit(WPI_TalonFX falcon) {
    falcon.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 39, 40, 10));
    falcon.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 70, 90, 1));
  }
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    setFalconLimit(frontRight); // Set Falcon Current Limits (Front Right)
    setFalconLimit(backRight); // Set Falcon Current Limits (Back Right)
    setFalconLimit(frontLeft); // Set Falcon Current Limits (Front Left)
    setFalconLimit(backLeft); // Set Falcon Current Limits (Back Left)
    rightMotors.setInverted(true); // Invert Right Motors
    m_shift.set(Value.kReverse); // Set Shifter to High Gear
    NetworkTableInstance inst = NetworkTableInstance.getDefault(); // Get Network Table Instance
    NetworkTable table = inst.getTable("DriveData"); // Get LiveData Table
    yawEntry = table.getEntry("Yaw"); // Get Yaw Entry
    pitchEntry = table.getEntry("Pitch"); // Get Pitch Entry
    rollEntry = table.getEntry("Roll"); // Get Roll Entry
    setCoast(NeutralMode.Coast);
  }
  // Shift Gears Command
  public CommandBase ShiftGears() {
    return runOnce(
      () -> {
        shift();
      });
  }
  // Change Brake/Coast Mode
  public CommandBase ChangeMode() {
    return run(
      () -> {
        setBrake(NeutralMode.Brake);
    });
  }
  // Periodic Function
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double yaw = m_pigeon.getYaw(); // Get Yaw Periodically
    yawEntry.setDouble(yaw); // Set Yaw Entry
    double pitch = m_pigeon.getPitch(); // Get Pitch Periodically
    pitchEntry.setDouble(pitch); // Set Pitch Entry
    double roll = m_pigeon.getRoll(); // Get Roll Periodically
    rollEntry.setDouble(roll); // Set Roll Entry
    pitchValue = pitch; // Set Pitch Value
    onSlope();
    if (onSlope() == true) {
      setBrake(NeutralMode.Brake);
    } else {
      setCoast(NeutralMode.Coast);
    }
  }
  // onSlope Query
  public boolean onSlope() {
    return pitchValue < Constants.MINSEEKPITCH || pitchValue > Constants.MAXSEEKPITCH;
  }
  // Shift Gears Method
  public void shift() {
    m_shift.toggle();
    HighGear = !HighGear;
  }
  // ARCADE DRIVE
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }
  // TANK DRIVE
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(rightVolts);
    m_drive.feed();
  }
  // Set Brake Mode
  public void setBrake(NeutralMode mode) {
    frontRight.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
  }
  // Set Coast Mode
  public void setCoast(NeutralMode mode) {
    frontRight.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
  }
}
