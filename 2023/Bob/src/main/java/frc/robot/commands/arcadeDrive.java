// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants; // Constants File
import frc.robot.RobotContainer; // RobotContainer File

public class arcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  public arcadeDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive); // Add the RobotContainer's m_drive object as a requirement
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = RobotContainer.xbox.getRawAxis(1);
    double rotateSpeed = RobotContainer.xbox.getRawAxis(0);
    RobotContainer.m_drive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drive.arcadeDrive(0,0); // Stops the motors when the robot is disabled or enters teleop mode AFTER auto
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
