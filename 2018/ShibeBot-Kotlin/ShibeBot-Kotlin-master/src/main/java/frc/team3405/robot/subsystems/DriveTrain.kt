package frc.team3405.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3405.robot.Robot
import frc.team3405.robot.commands.DriveCommand
import kotlin.math.pow

class DriveTrain : Subsystem() {
    private val left1 by lazy {Robot.left1} //Right Bottom
    private val right1 by lazy {Robot.right1} //Left Top
    private val left2 by lazy {Robot.left2}
    private val right2 by lazy {Robot.right2}

    override fun initDefaultCommand() {
        defaultCommand = DriveCommand()
    }

    fun arcadeDrive() {
        val x = controlModifier(Robot.joystick.leftX)
        val y = controlModifier(-Robot.joystick.leftY)
        val maxOutput = .7
        val left: Double = (y - x) * maxOutput
        val right: Double = (y + x) * maxOutput * 1.04

        right1.set(right)
        right2.set(right)

        left1.set(-left)
        left2.set(-left)
    }

    //For Eastmond
    fun tankDrive() {
        val leftY = Robot.joystick.leftY * 0.7
        val rightY = Robot.joystick.rightY * 0.7
        val maxOutput = .7
        val left: Double = rightY * maxOutput
        val right: Double = leftY * maxOutput

        left1.set(left)
        left2.set(left)
        right1.set(-right)
        right2.set(-right)

    }

    private fun controlModifier(value: Double): Double {
        if (value < 0.0) {
            return(-value.pow(2.0))
        } else {
            return(value.pow(2.0))
        }
    }
}

