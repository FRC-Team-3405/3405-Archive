// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoCommands.*;
import frc.robot.commands.DrivetrainCommands.ArcadeDrive;
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
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final static Drivetrain m_drive = new Drivetrain();

  // Driver Controller
  public final static CommandXboxController airflo = new CommandXboxController(OperatorConstants.P_AIRFLO);
  // Secondary Controller
  public final static CommandXboxController xbox = new CommandXboxController(OperatorConstants.P_XBOX);
  // Autonomous Chooser
  private static SendableChooser<Command> m_autoChoice; // Autonomous Chooser

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Set the default commands
    m_drive.setDefaultCommand(new ArcadeDrive());
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
    airflo.rightBumper().onTrue(m_drive.ShiftGears()); // Shift Gears
  }

  public static void buildAutoTab() {
    ShuffleboardTab autoTab = Shuffleboard.getTab("Auto");
    m_autoChoice = new SendableChooser<Command>();
    m_autoChoice.addOption("Balance Sequence", new BalanceSequence()); // Drive balance
    m_autoChoice.addOption("Drive Forward", new DriveForward()); // Drive forward for 1.5 seconds
    m_autoChoice.setDefaultOption("Drive Forward", new DriveForward());
    autoTab.add("Autonomous Chooser", m_autoChoice).withWidget(BuiltInWidgets.kComboBoxChooser).withSize(2,1);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new ArcadeDrive();
  }
}
