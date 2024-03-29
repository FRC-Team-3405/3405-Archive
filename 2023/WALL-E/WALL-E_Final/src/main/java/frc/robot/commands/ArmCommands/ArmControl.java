// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.AC;
public class ArmControl extends CommandBase {
  public static double rotateTarget = AC.DEF_ROT;
  public static double extendTarget = AC.DEF_EXT;
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
    if (ArmControl.rotateTarget - RobotContainer.m_operatorController.getLeftY()*AC.ROT_POWER > -0.5 &&
        ArmControl.rotateTarget - RobotContainer.m_operatorController.getLeftY()*AC.ROT_POWER < 12.5) {
      ArmControl.rotateTarget += -RobotContainer.m_operatorController.getLeftY()*AC.ROT_POWER;
    }
    // if (ArmControl.extendTarget - RobotContainer.m_operatorController.getRightY()*AC.EXT_POWER > -0.5 &&
    //     ArmControl.extendTarget - RobotContainer.m_operatorController.getRightY()*AC.EXT_POWER < 43){
    //   ArmControl.extendTarget += -RobotContainer.m_operatorController.getRightY()*AC.EXT_POWER;
    // } // Uncomment for extension limits when we have reeled the arm in
    ArmControl.extendTarget += -RobotContainer.m_operatorController.getRightY()*AC.EXT_POWER;
    RobotContainer.m_arm.setRotatePIDControl(ArmControl.rotateTarget);
    RobotContainer.m_arm.setExtendPIDControl(ArmControl.extendTarget);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Note: We could possibly return a 'true' value for an isFinished variable to prevent the arm moving after disabling...
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
