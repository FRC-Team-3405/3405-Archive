// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
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
  public final static Drivetrain m_drive = new Drivetrain();
  private static SendableChooser<Command> m_autoChoice;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static CommandJoystick joystick =
      new CommandJoystick(OperatorConstants.P_JOYSTICK);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Set Default Commands
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
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    joystick.button(1).onTrue(m_drive.ShiftGears());
  }

  /* METHOD FOR BUILDING THE DRIVER DASHBOARD DURING BOOT-UP */
  public void buildDriverTab() {
    // Build Driver Dashboard
    ShuffleboardTab driveDash = Shuffleboard.getTab("DriverDash");
    // Auto Routine Chooser
    m_autoChoice = new SendableChooser<Command>();
    m_autoChoice.setDefaultOption("Default Auto", new DriveForward()); // DEFAULT AUTO
    m_autoChoice.addOption("Drive Forward", new DriveForward()); // DRIVE FORWARD
    m_autoChoice.addOption("Drive Backward", new DriveBackward()); // DRIVE BACKWARD
    m_autoChoice.addOption("Self Destruct", new SelfDestruct()); // SELF DESTRUCT
    driveDash.add("Auto Selector", m_autoChoice).withPosition(1,5).withWidget(BuiltInWidgets.kComboBoxChooser).withSize(3,1);
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
