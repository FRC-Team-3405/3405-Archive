// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final static DriveTrain m_drivetrain = new DriveTrain();
  // Auto Routine Chooser
  private static SendableChooser<Command> m_autoChoice;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static CommandXboxController xbox =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Set default commands on subsystems
    m_drivetrain.setDefaultCommand(new ArcadeDrive());
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
    // Schedule `DriveForward` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition).onTrue(new DriveForward());

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    xbox.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /* METHOD FOR BUILDING THE DRIVER DASHBOARD DURING ROBOT BOOT-UP */
  public void buildDriverTab() {
    /* Build Driver Dashboard */
    ShuffleboardTab driveDash = Shuffleboard.getTab("DriveDash");
    m_autoChoice = new SendableChooser<Command>();
    m_autoChoice.setDefaultOption("Default Auto", new DriveForward()); // DEFAULT AUTO
    m_autoChoice.addOption("Drive Forward", new DriveForward()); // DRIVE FORWARD AUTO
    m_autoChoice.addOption("Drive Backward", new DriveBackward()); // DRIVE BACKWARD AUTO
    driveDash.add("Auto Selector", m_autoChoice).withPosition(0,2).withWidget(BuiltInWidgets.kComboBoxChooser).withSize(2,1);
    /* driveDash Components */
    // DriveTrain Components
    driveDash.add("Comp Enable", m_drivetrain.getCompressorVal()).withPosition(0,0).withWidget(BuiltInWidgets.kBooleanBox);
    driveDash.add("Comp Volts", m_drivetrain.getCompressorVolts()).withPosition(0,1).withWidget(BuiltInWidgets.kBooleanBox);
    driveDash.add("YAW", m_drivetrain.getYaw()).withPosition(1,0).withWidget(BuiltInWidgets.kGyro);
    driveDash.add("PITCH", m_drivetrain.getPitch()).withPosition(3,0).withWidget(BuiltInWidgets.kGyro);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoChoice.getSelected();
  }
}
