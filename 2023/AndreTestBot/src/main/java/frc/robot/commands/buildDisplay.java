// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.testAutoRoutines.*;

public class buildDisplay extends CommandBase {
  public static SendableChooser<Command> m_autoChoice; // Auto Routine Chooser
  
    ShuffleboardTab driveDash = Shuffleboard.getTab("Drive");

  /** Creates a new buildDisplay. */
  public buildDisplay() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Auto Routine Chooser
    m_autoChoice = new SendableChooser<Command>();
    m_autoChoice.setDefaultOption("Default Auto", new DriveForward()); // DEFAULT AUTO
    m_autoChoice.addOption("Drive Forward", new DriveForward()); // DRIVE FORWARD
    m_autoChoice.addOption("Drive Backward", new DriveBackward()); // DRIVE BACKWARD
    m_autoChoice.addOption("Self Destruct", new SelfDestruct()); // SELF DESTRUCT
    m_autoChoice.addOption("Begin Balance", new BeginBalance()); // BEGIN BALANCE
    driveDash.add("Auto Selector", m_autoChoice).withPosition(5,0).withWidget(BuiltInWidgets.kComboBoxChooser).withSize(3,1);
    driveDash.add("Pitch", RobotContainer.m_drive.getPitch()).withPosition(0,0).withSize(2,2);
    System.out.println("Pitch created");
    driveDash.add("Roll", RobotContainer.m_drive.getRoll()).withPosition(0,5).withSize(2,2);
    System.out.println("Roll created");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //driveDash.add("Pitch", RobotContainer.m_drive.getPitch()).withPosition(0,0).withSize(2,2).withWidget(BuiltInWidgets.kGyro).getEntry();
    //driveDash.add("Roll", RobotContainer.m_drive.getRoll()).withPosition(2,0).withSize(2,2).withWidget(BuiltInWidgets.kGyro).getEntry();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
