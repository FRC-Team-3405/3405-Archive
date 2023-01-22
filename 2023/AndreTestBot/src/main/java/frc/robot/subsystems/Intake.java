package frc.robot.subsystems;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  public static DoubleSolenoid extender;
  public static Boolean extended = false;

  private static WPI_TalonSRX motor = new WPI_TalonSRX(Constants.INTAKE_SRX);

  public Intake() {
    extender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.INTAKE_IN,Constants.INTAKE_OUT);
    extender.set(kForward);
  }

  // Toggle Intake Command
  public CommandBase ToggleIntake() {
    return runOnce(
        () -> {
          extend();
        });
  }

  public void setMotor() {
    //** TALONSRX CURRENT LIMITS (Intake Motor) */
    motor.configPeakCurrentLimit(30); // don't activate current limit until current exceeds 30 A ...
    motor.configPeakCurrentDuration(100); // ... for at least 100 ms
    motor.configContinuousCurrentLimit(15); // once current-limiting is actived, hold at 20A
    motor.enableCurrentLimit(true);

    if (extended) {
      motor.set(ControlMode.PercentOutput, -1.0); // Intake Motor
    } else {
      motor.set(ControlMode.PercentOutput, 0.0);
    }
  }
  // Extend or retract the intake
  public void extend() {
    extender.toggle();
    extended = !extended;
  }
  // Get the status of the intake
  public boolean getIntakeStatus() {
    return extended;
  }
}
