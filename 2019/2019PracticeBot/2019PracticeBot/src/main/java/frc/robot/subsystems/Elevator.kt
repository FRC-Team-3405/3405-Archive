package frc.robot.subsystems

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.RunElevatorCommand
import frc.robot.maps.MAX_ELEVATOR_SPEED
import frc.robot.utilties.ReportableSubsystem

class Elevator: ReportableSubsystem() {

    override fun initDefaultCommand() {
        defaultCommand = RunElevatorCommand()
    }

//    private val elevatorMotor = TalonSRX(ELEVATOR_MOTOR_PORT)

    fun driveElevator() {
//        println("Driving elevator at speed: ${Robot.secondaryJoystick.y * MAX_ELEVATOR_SPEED}")
    }

    fun setElevatorHeight(inches: Double) {

    }

    override fun report() {
        SmartDashboard.putBoolean("Physical Elevator Enabled", false)
    }
}