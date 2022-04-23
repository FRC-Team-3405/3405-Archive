package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunBoxCommand: Command() {
    init {
        requires(Robot.box)
    }

    override fun execute() {
        Robot.box.rotateBox()
        Robot.box.report()
    }

    override fun isFinished() = false
}