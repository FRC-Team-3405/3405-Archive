package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunFeederCommand: Command() {
    init {
        requires(Robot.feeder)
    }

    override fun execute() {
        Robot.feeder.report()
        Robot.feeder.runFeeder()
    }

    override fun interrupted() {
        super.interrupted()
        println("My feeder hath been interrupted...")
    }

    override fun isInterruptible() = true

    override fun isFinished() = false
}
