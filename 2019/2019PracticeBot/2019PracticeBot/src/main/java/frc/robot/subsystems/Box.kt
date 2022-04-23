package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.RunBoxCommand
import frc.robot.maps.RobotMap.BOX_MOTOR_PORT
import frc.robot.utilties.ReportableSubsystem

class Box: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunBoxCommand()
    }

    private val boxRotatorMotor = VictorSPX(BOX_MOTOR_PORT)
//    private val beltMotor = Spark(BOX_BELT_MOTOR_PORT)

    fun rotateBox() {
        if(Robot.secondaryJoystick.eightButton.get()) {
            boxRotatorMotor.set(ControlMode.PercentOutput, Robot.secondaryJoystick.x * 0.9)
        } else {
            boxRotatorMotor.set(ControlMode.PercentOutput, Robot.secondaryJoystick.x * 0.5)
        }
    }

    override fun report() {
        SmartDashboard.putNumber("VictorSPX Output", boxRotatorMotor.motorOutputPercent)
    }
}