// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class arcadeDrive extends CommandBase {
  /** Creates a new arcadeDrive. */
  public arcadeDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_robotDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Set the fwd speed
    double fwd = -RobotContainer.xbox.getRawAxis(Constants.X_XAXIS)*Constants.S_MAXPOWER;
    // Set the rotate speed
    double rot = RobotContainer.xbox.getRawAxis(Constants.X_YAXIS)*Constants.S_MAXTURNPOWER;
    // Return the axis values to the RobotContainer
    RobotContainer.m_robotDrive.arcadeDrive(fwd, rot);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_robotDrive.arcadeDrive(0,0); // Stops the motors while disabled
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
