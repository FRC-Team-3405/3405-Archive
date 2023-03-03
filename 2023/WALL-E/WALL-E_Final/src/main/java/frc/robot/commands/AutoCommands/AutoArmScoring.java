// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.Constants.AC;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoArmScoring extends SequentialCommandGroup {
  /** Creates a new AutoArmScoring. */
  public AutoArmScoring() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new AutoArmRotate(AC.AUTO_ROT_POS), new AutoArmExtend(AC.AUTO_EXT_POS), RobotContainer.m_pneumatics.ToggleClaw(), new AutoArmRotate(AC.AUTO_ROT_ADJ_POS), new AutoScoreBackUp(), RobotContainer.m_pneumatics.ToggleClaw());
  }
}
