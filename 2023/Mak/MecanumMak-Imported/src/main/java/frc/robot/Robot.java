// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** A mecanum drive sounds complicated in theory. In reality, it is actually
 * quite easy to implement in a Timed Robot program. This example code, written
 * by Samuel Whitaker (Team 3405), implements old Talon SR controllers using the
 * base Talon class in WPI's Motor Control methods. The drive uses a Cartesian
 * method of control with a standard Joystick. Our team utilizes a MadCatz joystick,
 * which contains a third axis of rotation accessed through the rotation of the stick
 * itself. Feel free to modify the code to fit your needs.
 */

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 1; // was 3; // Front Left Motor Channel
  private static final int kRearLeftChannel = 3; // was 0; // Rear Left Motor Channel
  private static final int kFrontRightChannel = 2; // was 1; // Front Right Motor Channel
  private static final int kRearRightChannel = 0; // was 2; // Rear Right Motor Channel
  private static final int kJoystickChannel = 0; // Joystick Port
  private MecanumDrive m_drive; // MecanumDrive Object
  private Joystick m_stick; // Joystick Object
  @Override
  public void robotInit() {
    Talon frontLeft = new Talon(kFrontLeftChannel); // Instantiate the Front Left Motor
    Talon rearLeft = new Talon(kRearLeftChannel); // Instantiate the Rear Left Motor
    Talon frontRight = new Talon(kFrontRightChannel); // Instantiate the Front Right Motor
    Talon rearRight = new Talon(kRearRightChannel); // Instantiate the Rear Right Motor
    frontRight.setInverted(true); // Invert the Front Right Motor
    rearRight.setInverted(true); // Invert the Rear Right Motor
    m_drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight); // Instantiate the MecanumDrive Object
    m_stick = new Joystick(kJoystickChannel); // Instantiate the Joystick Object
  }
  @Override
  public void robotPeriodic() {}
  @Override
  public void disabledInit() {}
  @Override
  public void disabledPeriodic() {}
  @Override
  public void autonomousInit() {}
  @Override
  public void autonomousPeriodic() {}
  @Override
  public void teleopInit() {}
  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
    m_drive.driveCartesian(-m_stick.getY(), m_stick.getX(), m_stick.getRawAxis(3)); // Axis 3 on MadCatz Joystick
  }
  @Override
  public void testInit() {}
  @Override
  public void testPeriodic() {}
}
