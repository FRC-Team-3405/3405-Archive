// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.AC;

public class IncrementPosition extends CommandBase {
  String component;
  public boolean finished = false;

  /** Creates a new IncrementPosition. */
  public IncrementPosition(String component) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_arm);
    this.component = component;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (component.equals("rotate")) {
      if (ArmControl.rotateTarget < AC.ROT_ONE) {
        ArmControl.rotateTarget = AC.ROT_ONE;
      } else if (ArmControl.rotateTarget >= AC.ROT_TWO &&
      ArmControl.rotateTarget < AC.ROT_TWO) {
        ArmControl.rotateTarget = AC.ROT_THREE;
      } else if (ArmControl.rotateTarget >= AC.ROT_THREE &&
      ArmControl.rotateTarget < AC.ROT_FOUR) {
        ArmControl.rotateTarget = AC.ROT_FOUR;
      }
    } else if (component.equals("extend")) {
      if (ArmControl.extendTarget < AC.EXT_ONE) {
        ArmControl.extendTarget = AC.EXT_ONE;
      } else if (ArmControl.extendTarget >= AC.EXT_ONE &&
      ArmControl.extendTarget < AC.EXT_TWO) {
        ArmControl.extendTarget = AC.EXT_TWO;
      } else if (ArmControl.extendTarget >= AC.EXT_TWO &&
      ArmControl.extendTarget < AC.EXT_THREE) {
        ArmControl.extendTarget = AC.EXT_THREE;
      } else if (ArmControl.extendTarget >= AC.EXT_THREE 
      && ArmControl.extendTarget < AC.EXT_FOUR) {
        ArmControl.extendTarget = AC.EXT_FOUR;
      }
    }
    finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}