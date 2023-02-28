// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.Constants.AC;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmReset extends InstantCommand {
  public ArmReset() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println(ArmControl.rotateTarget);
    System.out.println(ArmControl.extendTarget);
    ArmControl.rotateTarget = AC.DEF_ROT;
    ArmControl.extendTarget = AC.DEF_EXT;
    System.out.println("The arm has been reset.");
  }
}
