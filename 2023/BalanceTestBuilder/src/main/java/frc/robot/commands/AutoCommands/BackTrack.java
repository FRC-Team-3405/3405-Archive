// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BackTrack extends CommandBase {
  boolean isFinished = false;
  /** Creates a new CheckBalance. */
  public BackTrack() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.m_drive.pitchVal > Constants.MAXSECONDARYBALANCE){
      RobotContainer.m_drive.tankDriveVolts(-Constants.SECONDARYBALANCESPEED, -Constants.SECONDARYBALANCESPEED); // Drive backward
      System.out.println("Rolling, Rolling, Rolling...");
      return;
      } 
    if (RobotContainer.m_drive.pitchVal < Constants.MAXSECONDARYBALANCE){
      RobotContainer.m_drive.tankDriveVolts(0,0);
      isFinished = true;
      System.out.println("Yo! I am finished, brother!");
      return;
    } 
    // else if (RobotContainer.m_drive.pitchVal > Constants.MAXBALANCEPITCH){
    //   RobotContainer.m_drive.tankDriveVolts(-Constants.AUTOBALANCESPEED, -Constants.AUTOBALANCESPEED); // Drive backward if necessary
    //   isFinished = false;
    //   return;
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isFinished = false;
    RobotContainer.m_drive.tankDriveVolts(0, 0); // Stop the robot when the command ends
    System.out.println("I have been interrupted, good sir.");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
