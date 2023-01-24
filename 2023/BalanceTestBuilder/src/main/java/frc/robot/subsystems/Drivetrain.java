// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.WPI_Pigeon2;

public class Drivetrain extends SubsystemBase {
  /* Pneumatics */
  public static DoubleSolenoid m_shift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.LOWGEAR, Constants.HIGHGEAR);
  public static boolean LowGear = false; // Low Gear
  /* Drivetrain */
  // Motors
  private final WPI_TalonFX frontRight = new WPI_TalonFX(Constants.FR_TALONFX); // Front Right Talon FX
  private final WPI_TalonFX backRight = new WPI_TalonFX(Constants.BR_TALONFX); // Back Right Talon FX
  private final WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.FL_TALONFX); // Front Left Talon FX
  private final WPI_TalonFX backLeft = new WPI_TalonFX(Constants.BL_TALONFX); // Back Left Talon FX
  // Motor Controller Groups
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight); // Right Motors
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft); // Left Motors
  // Differential Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors); // Differential Drive
  // Pigeon 2.0 IMU
  private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(Constants.P_PIGEON); // Pigeon 2.0
  // Current Limits
  void setFalconLimit(WPI_TalonFX falcon) {
    falcon.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 39, 40, 10));
    falcon.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 70, 90, 1));
  }
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    setFalconLimit(frontRight); // Set Falcon Current Limits (FR)
    setFalconLimit(backRight); // Set Falcon Current Limits (BR)
    setFalconLimit(frontLeft); // Set Falcon Current Limits (FL)
    setFalconLimit(backLeft); // Set Falcon Current Limits (BL)
    m_shift.set(Value.kForward); // Set Shifter to Low Gear
  }

  // Shift Gears Command
  public CommandBase ShiftGears() {
    return runOnce(
        () -> {
          shift();
        });
  }

  // Query Status of Pitch; Return true if less than -5 or greater than 5.
  public boolean onSlope() {
    System.out.println("Pigeon is Active!");
    // Query the boolean state of the Pigeon2.0 IMU.
    return m_pigeon.getPitch() < Constants.MINPITCH || m_pigeon.getPitch() > Constants.MAXPITCH;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Shift Gears
  public void shift() {
    m_shift.toggle();
    LowGear = !LowGear;
  }

  /* ARCADE DRIVE */
  // Arcade Drive
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  // Set Brake Mode
  public void setBrake() {
    frontRight.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    System.out.println("Drivetrain has set Neutral Mode to Brake.");
  }
  // Set Coast Mode
  public void setCoast() {
    frontRight.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    System.out.println("Drivetrain has set Neutral Mode to Coast.");
  }
}
