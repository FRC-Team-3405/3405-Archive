package frc.robot.subsystems

import edu.wpi.first.wpilibj.AnalogGyro
import edu.wpi.first.wpilibj.AnalogInput
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.RunGyroscopeCommand
import frc.robot.maps.MAIN_GYRO_NAME
import frc.robot.maps.RobotMap.GYROSCOPE_PORT
import frc.robot.utilties.ReportableSubsystem

class Gyroscope: ReportableSubsystem() {
    override fun initDefaultCommand() {
        gyroscope.name = MAIN_GYRO_NAME
        defaultCommand = RunGyroscopeCommand()
    }

    private val gyroscope = AnalogGyro(AnalogInput(GYROSCOPE_PORT))

    fun initGryo() {
        gyroscope.initGyro()
    }

    fun calibrate() {
        gyroscope.calibrate()
    }

    fun reset() {
        gyroscope.reset()
    }

    fun getAngle(): Double {
        return gyroscope.angle
    }

    fun getRate(): Double {
        return gyroscope.rate
    }

    override fun report() {
        SmartDashboard.putNumber("gyro_angle", gyroscope.angle)
        SmartDashboard.putNumber("gyro_offset", gyroscope.offset)
        SmartDashboard.putNumber("gyro_rate", gyroscope.rate)
        SmartDashboard.putNumber("gyro_pid", gyroscope.pidGet())
        SmartDashboard.putNumber("gyro_center", gyroscope.center.toDouble())
    }

}
