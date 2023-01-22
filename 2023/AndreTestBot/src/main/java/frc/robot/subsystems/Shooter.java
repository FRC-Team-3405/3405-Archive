// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  // Shooter Motors
  WPI_TalonFX rightShooter = new WPI_TalonFX(Constants.RS_TALONFX); // Right Shooter TalonFX
  WPI_TalonFX leftShooter = new WPI_TalonFX(Constants.LS_TALONFX); // Left Shooter TalonFX
  WPI_TalonSRX lowerTowerMotor = new WPI_TalonSRX(Constants.LTM_TALONSRX); // Lower Tower Motor TalonSRX
  WPI_TalonSRX upperTowerMotor = new WPI_TalonSRX(Constants.UTM_TALONSRX); // Upper Tower Motor TalonSRX
  WPI_VictorSPX strayTowerMotor = new WPI_VictorSPX(Constants.STM_VICTORSPX); // Intake VictorSPX
  // Motor Controller Groups
  MotorControllerGroup tower = new MotorControllerGroup(lowerTowerMotor, upperTowerMotor); // Tower Motor Controller Group
  MotorControllerGroup shooter = new MotorControllerGroup(rightShooter, leftShooter); // Shooter Motor Controller Group
  /** Creates a new Shooter. */
  public Shooter() {
    leftShooter.configStatorCurrentLimit(
            new StatorCurrentLimitConfiguration(true, 70, 90, 1.0)
        );
        leftShooter.configSupplyCurrentLimit(
            new SupplyCurrentLimitConfiguration(true, 39, 40, 10)
        );
        rightShooter.configStatorCurrentLimit(
            new StatorCurrentLimitConfiguration(true, 70, 90, 1.0)
        );
        rightShooter.configSupplyCurrentLimit(
            new SupplyCurrentLimitConfiguration(true, 39, 40, 10)
        );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  // Shoot Method
  public void shoot(double speed) {
    rightShooter.set(speed);
    leftShooter.set(speed);
  }
  // Shooter Feed Method
  public void tower(double speed) {
    strayTowerMotor.set(speed);
    lowerTowerMotor.set(speed);
    upperTowerMotor.set(speed);
  }
}
