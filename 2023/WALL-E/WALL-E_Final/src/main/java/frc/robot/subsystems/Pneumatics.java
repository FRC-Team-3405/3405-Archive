// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  private final PneumaticHub ph = new PneumaticHub(); // REV Pneumatic Hub
  // private final Compressor compressor = new Compressor(PneumaticsModuleType.REVPH); // Compressor
  private final DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1); // Shifter Solenoid
  private final DoubleSolenoid claw = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3); // Claw Solenoid
  public static boolean LowGear = false; // HighGear is true, LowGear is false (Set to High Gear by default)
  public static boolean ClawOpen = false; // ClawClosed is true, ClawOpen is false (Set to Claw Closed by default)
  /** Creates a new Pneumatics. */
  public Pneumatics() {
    ph.clearStickyFaults();
    shifter.set(DoubleSolenoid.Value.kForward); // Set to High Gear by Default
    claw.set(DoubleSolenoid.Value.kForward); // Set to Claw Closed by Default
  }
  // Shifts to Low or High gear
  public CommandBase ShiftGears() {
    return runOnce(
      () -> {
        shift();
      });
  }
  // Opens or closes claw
  public CommandBase ToggleClaw() {
    return runOnce(
      () -> {
        toggle();
      });
  }
  /** PERIODIC FUNCTION */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  // Shift Function
  public void shift() {
    shifter.toggle();
    LowGear = !LowGear;
  }
  // Toggle Claw Function
  public void toggle() {
    claw.toggle();
    ClawOpen = !ClawOpen;
  }
}
