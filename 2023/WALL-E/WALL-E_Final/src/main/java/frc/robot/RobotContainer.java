// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoRoutines.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static Drivetrain m_drive = new Drivetrain(); // Drive
  public final static Pneumatics m_pneumatics = new Pneumatics(); // Pneumatics
  public final static Arm m_arm = new Arm(); // Arm
  private static SendableChooser<Command> m_autoChoice; // Autonomous Routine Chooser

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort); // Driver Controller
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort); // Operator Controller

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Driver Controller Button Bindings
    m_driverController.rightBumper().onTrue(m_pneumatics.ShiftGears());
    // Operator Controller Button Bindings
    m_operatorController.rightBumper().onTrue(m_pneumatics.ToggleClaw());
  }

  public static void buildAutoTab() {
    ShuffleboardTab autoTab = Shuffleboard.getTab("Auto");
    m_autoChoice = new SendableChooser<Command>();
    m_autoChoice.setDefaultOption("Custom 1", new X1()); // Custom Sequence 1 (From Auto Routines)
    m_autoChoice.addOption("Custom 1", new X1()); // Custom Sequence 1 (From Auto Routines)
    m_autoChoice.addOption("Custom 2", new X2()); // Custom Sequence 2 (From Auto Routines)
    m_autoChoice.addOption("Blue 1", new B1()); // Blue 1 (From Auto Routines)
    m_autoChoice.addOption("Blue 2", new B2()); // Blue 2 (From Auto Routines)
    m_autoChoice.addOption("Blue 3", new B3()); // Blue 3 (From Auto Routines)
    m_autoChoice.addOption("Red 1", new R1()); // Red 1 (From Auto Routines)
    m_autoChoice.addOption("Red 2", new R2()); // Red 2 (From Auto Routines)
    m_autoChoice.addOption("Red 3", new R3()); // Red 3 (From Auto Routines)
    autoTab.add("Auto Chooser", m_autoChoice).withWidget(BuiltInWidgets.kComboBoxChooser).withSize(2, 1);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_autoChoice.getSelected();
  }
}
