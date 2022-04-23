package frc.robot.subsystems

import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.utilties.ReportableSubsystem
import frc.robot.utilties.TwoStatePneumatic

class Pneumatics: ReportableSubsystem() {
    override fun initDefaultCommand() {
        println("Pneumatics initialized")
    }

//    private val compressor = Compressor(COMPRESSOR_PORT)
//    private val shifter = TwoStatePneumatic(DoubleSolenoid(SHIFTER_OUT, SHIFTER_IN))
//    private val puncher = TwoStatePneumatic(DoubleSolenoid(PUNCHER_OUT, PUNCHER_IN), "puncher")
//    private val grabber = TwoStatePneumatic(DoubleSolenoid(GRABBER_OUT, GRABBER_IN), "grabber")

    fun shiftHighGear() {
        println("Shifted to high gear")
        report()
    }

    fun shiftLowGear() {
        println("Shifted to low gear")
        report()
    }

    fun isHighGear() = false

    fun toggleShift() {
        println("Toggling shifter")
        report()
    }

    fun punch() {
        println("Punch!")
        report()
    }

    fun unPunch() {
        println("UnPunch!")
        report()
    }

    fun grab() {
        println("Grabbing!")
        report()
    }

    fun retract() {
        println("Retracting")
        report()
    }

    override fun report() {
        SmartDashboard.putBoolean("High Gear", isHighGear())
        SmartDashboard.putBoolean("Physical Pneumatics Enabled", false)
    }

    companion object {
        private const val SENSOR_VOLTAGE = 5.0
        private fun analogToUnitPSI(voltage: Double): Double {
            return 250 * (voltage / SENSOR_VOLTAGE) - 25
        }
    }


}
