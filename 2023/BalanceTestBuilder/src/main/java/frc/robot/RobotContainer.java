// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoCommands.*;
import frc.robot.commands.DrivetrainCommands.*;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final static Drivetrain m_drive = new Drivetrain();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static CommandXboxController xbox =
      new CommandXboxController(OperatorConstants.P_XBOX);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Set the default commands
    m_drive.setDefaultCommand(new arcadeDrive());
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
    // Schedule `setBrake` when `onSlope` changes to `true`
    // new Trigger(m_drive::onSlope).onTrue(new setBrake()); // USE IN FUTURE TESTS
    // Schedule `setCoast` when `onSlope` changes to `false`
    // new Trigger(m_drive::onSlope).onFalse(new setCoast()); // USE IN FUTURE TESTS
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    xbox.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    xbox.rightBumper().onTrue(m_drive.ShiftGears());
    xbox.leftBumper().whileTrue(m_drive.ChangeMode()); // Set Brake Mode while left bumper is pressed, Set Coast Mode when left bumper is released

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // arcadeDrive will run in autonomous
    return new AutoBalance();
  }
}
