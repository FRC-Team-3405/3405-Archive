// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class arcadeDrive extends CommandBase {
  /** Creates a new arcadeDrive. */
  public arcadeDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = -RobotContainer.xbox.getRawAxis(OperatorConstants.XAXIS)*Constants.MAXPOWER; // Limit the moveSpeed to 80% Power
    double rotateSpeed = -RobotContainer.xbox.getRawAxis(OperatorConstants.YAXIS)*Constants.MAXPOWER; // Limit the rotateSpeed to 80% Power
    RobotContainer.m_drive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drive.arcadeDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
