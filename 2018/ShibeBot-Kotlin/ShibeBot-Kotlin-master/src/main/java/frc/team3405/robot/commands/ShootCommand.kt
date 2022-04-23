package frc.team3405.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team3405.robot.Robot

class ShootCommand : Command() {

    init {
        requires(Robot.Launcher)
    }

    override fun execute() {
        if(Robot.ArcadeDrive) {
            Robot.Launcher.arcadeDrive()
        } else {
            Robot.Launcher.tankDrive()
        }
    }

    override fun isFinished(): Boolean = false
}