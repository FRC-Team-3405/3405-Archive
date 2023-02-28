// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ArmExtend extends CommandBase {
  /** Creates a new ArmExtend. */
  public ArmExtend() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.m_arm.leftExtensionEncoder.getPosition() > -44.0) {
      RobotContainer.m_arm.setSpeed(0.2);
    }
    else{
      RobotContainer.m_arm.setSpeed(-0.1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_arm.setSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
