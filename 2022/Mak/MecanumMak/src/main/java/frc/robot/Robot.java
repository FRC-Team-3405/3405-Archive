// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 2;
  private static final int kRearLeftChannel = 3;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 0;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private XboxController m_stick;

  @Override
  public void robotInit() {
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(kFrontLeftChannel);
    WPI_VictorSPX rearLeft = new WPI_VictorSPX(kRearLeftChannel);
    WPI_VictorSPX frontRight = new WPI_VictorSPX(kFrontRightChannel);
    WPI_VictorSPX rearRight = new WPI_VictorSPX(kRearRightChannel);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new XboxController(kJoystickChannel);
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    //m_robotDrive.driveCartesian(POVrawValues()[0], POVrawValues()[1], m_stick.getRawAxis(4),0.0);
    m_robotDrive.driveCartesian(-m_stick.getRawAxis(1), m_stick.getRawAxis(0), m_stick.getRawAxis(4), 0.0);
    SmartDashboard.putNumber("POVY", m_stick.getPOV());
    SmartDashboard.putNumber("FwdBack", -m_stick.getRawAxis(1));
    SmartDashboard.putNumber("LeftRight", -m_stick.getRawAxis(0));
    //m_robotDrive.driveCartesian(-m_stick.getPOV(1), m_stick.getPOV(0), m_stick.getRawAxis(4), 0.0);
  }
  public double[] POVrawValues(){
    double throttle = m_stick.getRawAxis(3);
    SmartDashboard.putNumber("Throttle", throttle);
    double[] values = {0.0,0.0};
    int[] POVs = {0,45,90,135,180,225,270,315};
    int POV = m_stick.getPOV();
    switch (POV){
      case 0:
        values[0] = 1.0 * throttle;
        values[1] = 0.0 * throttle;
        return values;
      case 45:
        values[0] = 1.0 * throttle;
        values[1] = 1.0 * throttle;
        return values;
      case 90:
        values[0] = 0.0 * throttle;
        values[1] = 1.0 * throttle;
        return values;
      case 135:
        values[0] = 1.0 * throttle;
        values[1] = -1.0 * throttle;
        return values;
      case 180:
        values[0] = -1.0 * throttle;
        values[1] = 0.0 * throttle;
        return values;
      case 225:
        values[0] = -1.0 * throttle;
        values[1] = -1.0 * throttle;
        return values;
      case 270:
        values[0] = 0.0 * throttle;
        values[1] = -1.0 * throttle;
        return values;
      case 315:
        values[0] = 1.0 * throttle;
        values[1] = -1.0 * throttle;
        return values;
      default:
        values[0] = 0.0;
        values[1] = 0.0;
        return values;
    }
    

  }
}
