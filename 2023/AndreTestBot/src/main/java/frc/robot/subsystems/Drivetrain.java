// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX; // TalonFX
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration; // TalonFX Stator Current Limit
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration; // TalonFX Supply Current Limit
import com.ctre.phoenix.sensors.WPI_Pigeon2; // Pigeon 2.0 IMU

public class Drivetrain extends SubsystemBase {
  /* Pneumatics */
  private static Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM); // Compressor
  public static DoubleSolenoid m_shift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.LOWGEAR, Constants.HIGHGEAR); // Shifter
  public static boolean LowGear = false; // Low Gear
  /* Drivetrain */
  // Motors
  private final WPI_TalonFX frontRight = new WPI_TalonFX(Constants.FR_TALONFX); // Front Right TalonFX
  private final WPI_TalonFX backRight = new WPI_TalonFX(Constants.BR_TALONFX); // Back Right TalonFX
  private final WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.FL_TALONFX); // Front Left TalonFX
  private final WPI_TalonFX backLeft = new WPI_TalonFX(Constants.BL_TALONFX); // Back Left TalonFX
  // Motor Groups
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRight, backRight); // Right Motor Group
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeft, backLeft); // Left Motor Group
  // Differential Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(rightMotors, leftMotors); // Differential Drive
  // Encoders
  private final Encoder m_rightEncoder = new Encoder(0, 1, true); // Right Encoder
  private final Encoder m_leftEncoder = new Encoder(2, 3, false); // Left Encoder
  // Pigeon 2.0 IMU
  private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(Constants.P_PIGEON); // Pigeon 2.0 IMU
  // Gyro Sensor
  private final Gyro m_gyro = new ADXRS450_Gyro(); // Gyro Sensor
  // Current Limit
  void setFalconLimit(WPI_TalonFX falcon) {
    falcon.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 39, 40, 10));
    falcon.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 70, 90, 1));
  }
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    setFalconLimit(frontRight); // Limit the Front Right TalonFX
    setFalconLimit(backRight); // Limit the Back Right TalonFX
    setFalconLimit(frontLeft); // Limit the Front Left TalonFX
    setFalconLimit(backLeft); // Limit the Back Left TalonFX
    m_shift.set(Value.kForward); // Set the Shifter to Low Gear
    rightMotors.setInverted(true); // Invert the Right Motors
    resetEncoders(); // Reset the Encoders
  }

  // Shift Gears Command
  public CommandBase ShiftGears() {
    return runOnce(
        () -> {
          shift();
        });
  }

  /* ENCODER DATA */
  // Reset the Encoders to 0
  public void resetEncoders() {
    m_leftEncoder.reset(); // Reset the Left Encoder
    m_rightEncoder.reset(); // Reset the Right Encoder
  }
  // Get the Left Encoder Count
  public double getLeftEncoder() {
    return m_leftEncoder.get();
  }
  // Get the Right Encoder Count
  public double getRightEncoder() {
    return m_rightEncoder.get();
  }

  /* PNEUMATICS DATA */
  // Get Compressor Current
  public double getCurrent() {
    return m_compressor.getCurrent();
  }
  // Get Compressor Operation State
  public boolean getCompressorState() {
    return m_compressor.isEnabled();
  }
  // Get Compressor Pressure Switch State
  public boolean getPressureSwitch() {
    return m_compressor.getPressureSwitchValue();
  }
  // Shift Gears
  public void shift() {
    m_shift.toggle();
    LowGear = !LowGear;
  }
  // Get Gear State
  public boolean getShiftStatus() {
    return LowGear;
  }

  /* PIGEON DATA */
  // Get Pigeon Pitch
  public double getPitch() {
    return m_pigeon.getPitch();
  }
  // Get Pigeon Roll
  public double getRoll() {
    return m_pigeon.getRoll();
  }
  // Get Pigeon Yaw
  public double getYaw() {
    return m_pigeon.getYaw();
  }
  // Get Pigeon Temperature
  public double getTemp() {
    return ((m_pigeon.getTemp() * 9/5) + 32);
  }

  /* GYRO DATA */
  // Get Gyro Angle
  public double getAngle() {
    return m_gyro.getAngle();
  }
  // Reset Gyro Angle
  public void resetGyro() {
    m_gyro.reset();
  }

  /* ARCADE DRIVE */
  // Arcade Drive
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
