package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class ReleaseHatchPanelCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        GlobalScope.launch {
            Robot.pneumatics.grab()
            sleep(100)
            Robot.pneumatics.punch()
            sleep(250)
            Robot.pneumatics.retract()
            sleep(250)
            Robot.pneumatics.unPunch()
        }
    }

    override fun isFinished() = true
}