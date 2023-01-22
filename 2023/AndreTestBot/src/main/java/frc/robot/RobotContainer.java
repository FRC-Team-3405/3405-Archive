// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
// import frc.robot.commands.*;
import frc.robot.commands.testAutoRoutines.*;
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
  public static Drivetrain m_drive = new Drivetrain(); // Drivetrain
  public static Intake m_intake = new Intake(); // Intake
  // public static Shooter m_shooter = new Shooter(); // Shooter
  private static SendableChooser<Command> m_autoChoice; // Auto Routine Chooser

  // Controller
  public static CommandXboxController xbox = new CommandXboxController(OperatorConstants.P_XBOX);

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
    xbox.rightBumper().onTrue(m_drive.ShiftGears()); // Shift Gears
    xbox.b().onTrue(m_intake.ToggleIntake()); // Extend/Retract Intake
    xbox.a().onTrue(new DriveForward()); // Drive Forward for 1.5 seconds on button press TEST TEST TEST
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
    m_autoChoice.addOption("Begin Balance", new BeginBalance()); // BEGIN BALANCE
    driveDash.add("Auto Selector", m_autoChoice).withPosition(1,5).withWidget(BuiltInWidgets.kComboBoxChooser).withSize(3,1);
    // Heads-up Display
    driveDash.add("Pitch", m_drive.getPitch()).withPosition(0,0).withSize(2,2).withWidget(BuiltInWidgets.kGyro);
    driveDash.add("Roll", m_drive.getRoll()).withPosition(0,2).withSize(2,2).withWidget(BuiltInWidgets.kGyro);
    driveDash.add("Yaw", m_drive.getYaw()).withPosition(2,0).withSize(2,2).withWidget(BuiltInWidgets.kGyro);
    driveDash.add("Gyro Angle", m_drive.getAngle()).withPosition(2,2).withSize(2,2).withWidget(BuiltInWidgets.kGyro);
    driveDash.add("Pigeon Temp", m_drive.getTemp()).withPosition(0,4).withSize(1,1);
    driveDash.add("Left Encoder", m_drive.getLeftEncoder()).withPosition(1,4).withSize(1,1);
    driveDash.add("Right Encoder", m_drive.getRightEncoder()).withPosition(2,4).withSize(1,1);
    driveDash.add("Low Gear?", m_drive.getShiftStatus()).withPosition(3,4).withSize(1,1).withWidget(BuiltInWidgets.kBooleanBox);
    driveDash.add("Compressor Status", m_drive.getCompressorState()).withPosition(2,5).withSize(1,1).withWidget(BuiltInWidgets.kBooleanBox);
    driveDash.add("Compressor Current", m_drive.getCurrent()).withPosition(2,6).withSize(1,1);
    driveDash.add("Pressure Switch", m_drive.getPressureSwitch()).withPosition(2,7).withSize(1,1).withWidget(BuiltInWidgets.kBooleanBox);
    driveDash.add("Intake Status", m_intake.getIntakeStatus()).withPosition(3,5).withSize(1,1).withWidget(BuiltInWidgets.kBooleanBox);
    driveDash.add("Brake Mode?", m_drive.getNeutralMode()).withPosition(3,6).withSize(1,1).withWidget(BuiltInWidgets.kBooleanBox);
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
