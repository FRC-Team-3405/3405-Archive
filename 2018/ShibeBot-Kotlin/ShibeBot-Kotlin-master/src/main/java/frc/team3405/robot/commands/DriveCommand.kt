package frc.team3405.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team3405.robot.Robot
import frc.team3405.robot.Xbox
var ypressed = false
class DriveCommand : Command() {

    init {
        requires(Robot.driveTrain)
    }

    override fun execute() {
        if (Robot.joystick.joystick.getRawButton(Xbox.YButton) && !ypressed ) {
            ypressed = true
            Robot.ArcadeDrive = !Robot.ArcadeDrive
        }
        if (!Robot.joystick.joystick.getRawButton(Xbox.YButton)) {
            ypressed = false
        }
        if(Robot.ArcadeDrive) {
            Robot.driveTrain.arcadeDrive()
        } else {
            Robot.driveTrain.tankDrive()
        }
    }

    override fun isFinished(): Boolean = false
}