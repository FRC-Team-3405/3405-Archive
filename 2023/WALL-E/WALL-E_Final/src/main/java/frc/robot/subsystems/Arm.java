// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AC;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
  // Motors
  private final CANSparkMax rotator = new CANSparkMax(AC.ROT_RIGHT, MotorType.kBrushless);
  private final CANSparkMax rotatorFollower = new CANSparkMax(AC.ROT_LEFT, MotorType.kBrushless);
  private final CANSparkMax extender = new CANSparkMax(AC.EXT_LEFT, MotorType.kBrushless);
  private final CANSparkMax extenderFollower = new CANSparkMax(AC.EXT_RIGHT, MotorType.kBrushless);
  // Motor Encoder Values (NetworkTableEntry)
  private NetworkTableEntry leftExtension;
  private NetworkTableEntry rightExtension;
  private NetworkTableEntry leftRotation;
  private NetworkTableEntry rightRotation;
  // Motor Encoder Values (Raw Value)
  RelativeEncoder leftRotateEncoder = rotator.getEncoder();
  RelativeEncoder rightRotateEncoder = rotatorFollower.getEncoder();
  RelativeEncoder leftExtensionEncoder = extender.getEncoder();
  RelativeEncoder rightExtentionEncoder = extenderFollower.getEncoder();
  // PID Controllers
  SparkMaxPIDController rotatorPID = rotator.getPIDController();
  SparkMaxPIDController extenderPID = extender.getPIDController();
  /** Creates a new Arm. */
  public Arm() {
    // Rotator Setup
    rotatorFollower.follow(rotator, true); // Follows the Rotator Motor
    rotatorPID.setP(AC.ROT_P); // Rotator P Value
    rotatorPID.setI(AC.ROT_I); // Rotator I Value
    rotatorPID.setD(AC.ROT_D); // Rotator D Value
    rotatorPID.setFF(AC.ROT_FF); // Rotator FeedForward Value
    // Extender Setup
    extenderFollower.follow(extender, true); // Follows the Extender Motor
    extenderPID.setP(AC.EXT_P); // Extender P Value
    extenderPID.setI(AC.EXT_I); // Extender I Value
    extenderPID.setD(AC.EXT_D); // Extender D Value
    extenderPID.setFF(AC.EXT_FF); // Extender FeedForward Value
    // NetworkTable Setup
    NetworkTableInstance inst = NetworkTableInstance.getDefault(); // Get Default NetworkTable Instance
    NetworkTable table = inst.getTable("Arm"); // Get Arm Table
    leftExtension = table.getEntry("Left Extension"); // Get Left Extension Value
    rightExtension = table.getEntry("Right Extension"); // Get Right Extension Value
    leftRotation = table.getEntry("Left Rotation"); // Get Left Rotation Value
    rightRotation = table.getEntry("Right Rotation"); // Get Right Rotation Value
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Update NetworkTable Values
    leftExtension.setDouble(leftExtensionEncoder.getPosition()); // Set Left Extension Value
    rightExtension.setDouble(rightExtentionEncoder.getPosition()); // Set Right Extension Value
    leftRotation.setDouble(leftRotateEncoder.getPosition()); // Set Left Rotation Value
    rightRotation.setDouble(rightRotateEncoder.getPosition());  // Set Right Rotation Value
  }

  // Set Rotate Position Function
  public void setRotatePIDControl(double position) {
    rotatorPID.setReference(position, ControlType.kPosition); // Set Rotator Position
  }

  // Set Extend Position Function
  public void setExtendPIDControl(double position) {
    extenderPID.setReference(position, ControlType.kPosition); // Set Extender Position
  }

  public void setEncoders(String component, double position) {
    if (component.equals("rotate")) {
      leftRotateEncoder.setPosition(position);
      rightRotateEncoder.setPosition(position);
    } else if (component.equals("extend")) {
      leftExtensionEncoder.setPosition(position);
      rightExtentionEncoder.setPosition(position);

    }
  }
}
