package frc.team3405.robot.subsystems

import frc.team3405.robot.commands.SmartDashboardCommand
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3405.robot.Robot
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
//import edu.wpi.first.wpilibj.PowerDistributionPanel

class SmartDashboardSubsystem : Subsystem() {
    private val left1 by lazy{Robot.left1}
    private val right1 by lazy{Robot.right1}
    private val left2 by lazy{Robot.left2}
    private val right2 by lazy{Robot.right2}
    private val feeder by lazy{Robot.feeder}
    private val leftLauncher by lazy{Robot.leftLauncher}
    private val rightLauncher by lazy{Robot.rightLauncher}
    private val switch1 by lazy{Robot.switch1}
    private val switch2 by lazy{Robot.switch2}
    private val switch3 by lazy{Robot.switch3}
//    private val pdp = PowerDistributionPanel()
    override fun initDefaultCommand(){
        defaultCommand = SmartDashboardCommand()
    }

    fun run() {

        //joy sticks
        SmartDashboard.putNumber("leftx",Robot.joystick.leftX)
        SmartDashboard.putNumber("lefty",Robot.joystick.leftY)
        SmartDashboard.putNumber("rightx",Robot.joystick.rightX)
        SmartDashboard.putNumber("righty",Robot.joystick.rightY)
        //drive motors
        SmartDashboard.putNumber("LeftPrimary", left1.get())
        SmartDashboard.putNumber("RightPrimary", right1.get())
        SmartDashboard.putNumber("LeftSecondary", left2.get())
        SmartDashboard.putNumber("RightSecondary", right2.get())
        //launcher motors
        SmartDashboard.putNumber("FeederMotor", feeder.get())
        SmartDashboard.putNumber("LeftLauncherMotor", leftLauncher.get())
        SmartDashboard.putNumber("RightLauncherMotor", rightLauncher.get())
        //sensors
        SmartDashboard.putBoolean("Switch1", !switch1.get())
        SmartDashboard.putBoolean("Switch2", switch2.get())
        SmartDashboard.putBoolean("Switch3", switch3.get())
        //Power Distro Totals
/*        SmartDashboard.putNumber("Power Distro Temperature", pdp.temperature)
        SmartDashboard.putNumber("Power Distro Total Current", pdp.totalCurrent)
        SmartDashboard.putNumber("Power Distro Total Power", pdp.totalPower)
        SmartDashboard.putNumber("Power Distro Total Voltage", pdp.voltage)
        //Power Distro Port Current Draw
        SmartDashboard.putNumber("Power Distro Port 0 Draw", pdp.getCurrent(0))
        SmartDashboard.putNumber("Power Distro Port 1 Draw", pdp.getCurrent(1))
        SmartDashboard.putNumber("Power Distro Port 2 Draw", pdp.getCurrent(2))
        SmartDashboard.putNumber("Power Distro Port 3 Draw", pdp.getCurrent(3))
        SmartDashboard.putNumber("Power Distro Port 13 Draw", pdp.getCurrent(13))
        SmartDashboard.putNumber("Power Distro Port 14 Draw", pdp.getCurrent(14))
        SmartDashboard.putNumber("Power Distro Port 15 Draw", pdp.getCurrent(15))
*/

    }
}