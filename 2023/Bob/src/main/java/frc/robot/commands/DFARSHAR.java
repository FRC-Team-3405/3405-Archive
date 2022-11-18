// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DFARSHAR extends SequentialCommandGroup {
  /** Creates a new DFARSHAR. */
  public DFARSHAR() {
    addCommands(
      new DriveForward(),
      new DriveBackward()
    );
  }
}
