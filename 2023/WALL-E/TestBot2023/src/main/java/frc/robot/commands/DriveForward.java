// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveForward extends CommandBase {
  Timer t; // Create a timer variable
  /** Creates a new DriveForward. */
  public DriveForward() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain); // Add the DriveTrain Subsystem as a requirement
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    t = new Timer();  // Create a new timer
    t.start();  // Start the timer
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_drivetrain.tankDriveVolts(4, 4); // Drive forward at 4 volts
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    t.stop(); // Stop the timer
    RobotContainer.m_drivetrain.tankDriveVolts(0, 0); // Stop the robot when the command ends
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return t.hasElapsed(1.0); // Drive for 1 second
  }
}
