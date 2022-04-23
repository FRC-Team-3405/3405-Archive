package frc.robot.utilties

import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.SendableBase
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder

class LightSensor(port: Int): SendableBase() {

    private var sensor = DigitalInput(port)

    override fun initSendable(builder: SendableBuilder?) {
        builder?.setSmartDashboardType("Boolean")
        builder?.addBooleanProperty("Value", ::get, null)
    }

    fun get(): Boolean {
        return sensor.get()
    }

}
