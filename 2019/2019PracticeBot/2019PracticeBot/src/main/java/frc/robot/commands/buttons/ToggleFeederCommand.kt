package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class ToggleFeederCommand: Command() {
    init {
        requires(Robot.feeder)
    }

    override fun execute() {
        Robot.feeder.toggleFeeder()
    }

    override fun isFinished() = true

}
