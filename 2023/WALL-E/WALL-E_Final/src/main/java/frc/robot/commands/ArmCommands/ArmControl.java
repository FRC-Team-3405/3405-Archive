// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.AC;

public class ArmControl extends CommandBase {
  static double rotateTarget = AC.DEF_ROT;
  static double extendTarget = AC.DEF_EXT;
  /** Creates a new ArmControl. */
  public ArmControl() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ArmControl.rotateTarget += RobotContainer.m_operatorController.getLeftY()*AC.ROT_POWER;
    ArmControl.extendTarget += RobotContainer.m_operatorController.getRightY()*AC.EXT_POWER;
    RobotContainer.m_arm.setRotatePosition(ArmControl.rotateTarget);
    RobotContainer.m_arm.setExtendPosition(ArmControl.extendTarget);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_arm.setRotatePosition(ArmControl.rotateTarget); // Set 0 to the current position (TEST)
    RobotContainer.m_arm.setExtendPosition(ArmControl.extendTarget); // Set 0 to the current position (TEST)
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
