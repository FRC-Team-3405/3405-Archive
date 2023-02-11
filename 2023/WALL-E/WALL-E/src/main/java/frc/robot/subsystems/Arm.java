// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
  /* Pneumatics */
  public static DoubleSolenoid m_claw = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.ARM_IN, Constants.ARM_OUT); // Claw Solenoid
  public static boolean ClawClosed = false; // Claw Open is true, Claw Closed is false
  /* NEO Brushless Motors */
  private final CANSparkMax rotatorRight = new CANSparkMax(Constants.NEO_ROT_RIGHT, MotorType.kBrushless); // Rotater Right NEO Brushless Motor (Leader)
  private final CANSparkMax rotatorLeft = new CANSparkMax(Constants.NEO_ROT_LEFT, MotorType.kBrushless); // Rotater Left NEO Brushless Motor (Follower)
  private final CANSparkMax extenderRight = new CANSparkMax(Constants.NEO_EXT_RIGHT, MotorType.kBrushless); // Extender Right NEO Brushless Motor (Leader)
  private final CANSparkMax extenderLeft = new CANSparkMax(Constants.NEO_EXT_LEFT, MotorType.kBrushless); // Extender Left NEO Brushless Motor (Follower)
  private NetworkTableEntry extension; // Extension Data
  private NetworkTableEntry rotation; // Rotation Data
  /* PID Controllers */
  SparkMaxPIDController rotatorPID = rotatorRight.getPIDController(); // Extender PID Controller
  SparkMaxPIDController extenderPID = extenderRight.getPIDController(); // Rotator PID Controller
  RelativeEncoder rotatorEncoder = rotatorRight.getEncoder(); // Rotator Encoder
  RelativeEncoder extenderEncoder = extenderRight.getEncoder(); // Extender Encoder
  /* Breakbeam Sensor */
  private final DigitalInput breakbeamHigh = new DigitalInput(Constants.BREAKBEAMHIGH); // Breakbeam Sensor
  private final DigitalInput breakbeamLow = new DigitalInput(Constants.BREAKBEAMLOW); // Breakbeam Sensor

  /** Creates a new Arm. */
  public Arm() {
    /* Pneumatics */
    m_claw.set(DoubleSolenoid.Value.kForward); // Default to Claw Closed
    /* Rotator Motors */
    rotatorRight.setIdleMode(IdleMode.kBrake); // Rotator Right Motor Idle Mode
    rotatorLeft.setIdleMode(IdleMode.kBrake); // Rotator Left Motor Idle Mode
    rotatorLeft.follow(rotatorRight); // Rotator Left Motor Follows Rotator Right Motor
    rotatorPID.setP(Constants.ROTATOR_P); // Rotator P Value
    rotatorPID.setI(Constants.ROTATOR_I); // Rotator I Value
    rotatorPID.setD(Constants.ROTATOR_D); // Rotator D Value
    rotatorPID.setIZone(Constants.ROTATOR_I_ZONE); // Rotator I Zone (Error Tolerance)
    rotatorPID.setFF(Constants.ROTATOR_FF); // Rotator Feed Forward Value
    /* Extender Motors */
    extenderRight.setIdleMode(IdleMode.kBrake); // Extender Right Motor Idle Mode
    extenderLeft.setIdleMode(IdleMode.kBrake); // Extender Left Motor Idle Mode
    extenderLeft.follow(extenderRight); // Extender Left Motor Follows Extender Right Motor
    extenderPID.setP(Constants.EXTENDER_P); // Extender P Value
    extenderPID.setI(Constants.EXTENDER_I); // Extender I Value
    extenderPID.setD(Constants.EXTENDER_D); // Extender D Value
    extenderPID.setIZone(Constants.EXTENDER_I_ZONE); // Extender I Zone (Error Tolerance)
    extenderPID.setFF(Constants.EXTENDER_FF); // Extender Feed Forward Value
    /* NetworkTable Values */
    NetworkTableInstance instance = NetworkTableInstance.getDefault();
    NetworkTable table = instance.getTable("Arm");
    rotation = table.getEntry("Rotation");
    extension = table.getEntry("Extension");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    rotation.setDouble(rotatorEncoder.getPosition()); // Rotation Data
    extension.setDouble(extenderEncoder.getPosition()); // Extension Data
  }

  public void setRotatePosition(double position) {
    rotatorPID.setReference(position, ControlType.kPosition);
  }

  public void setExtendPosition(double position) {
    extenderPID.setReference(position, ControlType.kPosition);
  }

  public void toggleClaw() {
    m_claw.toggle();
    ClawClosed = !ClawClosed;
  }

  // Shift Gears Command
  public CommandBase ShiftGears() {
    return runOnce(
      () -> {
        toggleClaw();
      });
  }

}
